package sm.ciscoop.preno.hic.masks;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import sm.ciscoop.enumerati.EnumTipoLista;
import sm.ciscoop.pdc.Attributo;
import sm.ciscoop.pdc.Lista;
import sm.ciscoop.pdc.PDC;
import sm.ciscoop.pdc.PDCStatus;
import sm.ciscoop.pdc.Relazione;
import sm.ciscoop.pdc.Relazione1_1;
import sm.ciscoop.pdc.Relazione1_N;
import sm.ciscoop.pdc.StructAbil;
import sm.ciscoop.servlet.ListMask;
import sm.ciscoop.servlet.ajax.CISComando;
import sm.ciscoop.stdlibs.baseutils.lang.Classes;
import sm.ciscoop.stdlibs.baseutils.output.EnDataType;
import sm.ciscoop.stdlibs.baseutils.types.Text;


public abstract class AppDynamicListMask<P extends PDC> extends ListMask<P> implements IAppMask {

	private static final long	serialVersionUID	= 1L;

	private Class<P>					appPDCClass;
	private PDC								pdcRelazione;

	public boolean isCaricaSottolivelli() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public AppDynamicListMask() {
		appPDCClass = (Class<P>) Classes.getGenericClassType(this, 0);
	}

	/**
	 * Se ritorno falso non uso il metodo {@code createXMLFragment} e mi arrangio
	 * nel cosa faccio quando becco quel flag. Senza stare a ridefnire questo
	 * metodo in tutte le classi modifica il valore di ritorno nel metodo generale
	 * della lista dell'applicativo
	 */
	protected boolean isCaricareNuovoModo() {
		return true;
	}

	/**
	 * Di default ogni lista gestisce, tramite questa classe comune, il
	 * caricamento delle singole righe grazie ai metodi sottostanti (i nomi non
	 * sono il massimo... sorry!)
	 */
	@Override
	public void loadCombo() throws Exception {
		super.loadCombo();
		if (isCaricaSoloRelaz() && isCaricareNuovoModo()) {
			createXMLFragment();
		}
	}

	/**
	 * Metodo per caricare un PDC e le sue relazioni. In dei punti specifici
	 * invoca delle funzioni per permettere dei controlli al programmatore e non
	 * solo client
	 */
	public String createXMLFragment() throws Exception {
		StringBuffer xml = new StringBuffer();

		if ( !buildListaRelaz()) {
			return "";
		}

		//appendToResponse(pdcRelazione);
		if (Boolean.parseBoolean(getRequestParameterMap().getParameter("AlsoPdc"))) {
			appendToResponse("CaricaPdc", true);
		}
		return xml.toString();
	}

	@Override
	public Lista beforeLoadLista(Lista lista) throws Exception {
		if (isCaricaSottolivelli()) {
			return super.beforeLoadLista(lista);
		}
		for (Entry<String, StructAbil> r : lista.getRelazioni().getStructAbils().elements()) {
			Relazione rel = lista.getPDCModello().getRelazioneAnnidata(r.getValue().relName);
			if (rel == null) {
				continue;
			}
			if ( ! (rel instanceof Relazione1_N)) {
				continue;
			}
			rel.setEnabled(false);
		}
		return super.beforeLoadLista(lista);
	}

	@Override
	public Lista afterLoadLista(Lista lista) throws Exception {
		if (isCaricaSottolivelli()) {
			return super.afterLoadLista(lista);
		}
		Object relname[] = new Object[2];

		for (Entry<String, StructAbil> e : lista.getRelazioni().getStructAbils().elements()) {
			relname[0] = e.getValue().relName;
			relname[1] = e.getValue().pageSize;
			lista.enableStruct(relname, e.getValue().enabled);
		}
		return super.afterLoadLista(lista);
	}

	@CISComando(nome = "getXmlRelaz", envelopeType = EnDataType.JSON, payloadType = EnDataType.HTML, controllaPermessi = false)
	public final void getXmlRelaz() throws Exception {
		if ( !buildListaRelaz()) {
			return;
		}
		Lista l = new Lista(pdcRelazione);
		l.setPDCModello(pdcRelazione);
		l.add(pdcRelazione);
		l.setName("LstElementi");
		l.setTipoLista(EnumTipoLista.LISTA);
		l.putXMLAttr("categoria", "principale");

		appendToResponse(l);
	}

	public boolean buildListaRelaz() throws Exception {
		String pdcRelaz = getRequestParameterMap().getParameter("Pdc");
		String pdcPath = "";
		if ( !pdcRelaz.startsWith("sm.ciscoop")) {
			pdcPath = appPDCClass.getPackage().getName();
			pdcPath = pdcPath.substring(0, pdcPath.lastIndexOf("."));
			pdcPath += "." + pdcRelaz;
		} else {
			pdcPath = pdcRelaz;
		}
		Class<?> classPdc = Class.forName(pdcPath);
		pdcRelazione = (PDC) classPdc.getConstructor().newInstance();
		//Riempio il Pdc per caricarlo
		Set<String> names = getRequestParameterMap().keySet();
		for (String key : names) {
			String value = getRequestParameterMap().getParameter(key);

			Attributo attr = pdcRelazione.getAttributi().get(key);
			if (attr != null) {
				attr.setValue(value);
			}
		}

		// setWorkPDC(pdcRelazione);
		//fillPDC(true);

		Map<String, String> map_relazioni = getRequestParameterMap().getParameterRegex("relation_(.*)");
		Map<String, String> map_rowPerPage = getRequestParameterMap().getParameterRegex("rowsperpage_(.*)");
		Map<String, String> map_currentPage = getRequestParameterMap().getParameterRegex("currentpage_(.*)");

		if ( !possoCaricarePdc(pdcRelazione)) {
			return false;
		}

		for (String key : map_relazioni.keySet()) {
			if ( !possoAbilitareRelazione(map_relazioni.get(key))) {
				String[] sp = key.split("_");
				String idx = sp[sp.length - 1];

				if (map_relazioni.containsKey("relation_" + idx)) {
					map_relazioni.remove("relation_" + idx);
				}
				if (map_rowPerPage.containsKey("rowsperpage_" + idx)) {
					map_rowPerPage.remove("rowsperpage_" + idx);
				}
				if (map_currentPage.containsKey("currentpage_" + idx)) {
					map_currentPage.remove("currentpage_" + idx);
				}
			}
		}

		esaminaRelazioni(map_relazioni, map_rowPerPage, map_currentPage);

		pdcRelazione.setDMC(getDMC());

		if ( !caricaRelazioneManualmente(pdcRelazione, map_relazioni, map_rowPerPage, map_currentPage)) {
			return false;
		}

		if (map_relazioni.size() > 0) {
			List<Object> l_rels = new ArrayList<>();

			for (String key : map_relazioni.keySet()) {
				String[] sp = key.split("_");
				String idx = sp[sp.length - 1];

				l_rels.add(map_relazioni.get(key));
				int rpp = 0;

				if (map_rowPerPage.containsKey("rowsperpage_" + idx)) {
					rpp = Text.toInt(map_rowPerPage.get("rowsperpage_" + idx), 0);
				}
				l_rels.add(rpp);
			}

			pdcRelazione.enableStruct(l_rels.toArray(), true);
		}

		if ( !pdcRelazione.load() || pdcRelazione.getStatus() == PDCStatus.NO_DATA) {
			return false;
		}

		for (String key : map_currentPage.keySet()) {
			int npage = Text.toInt(map_currentPage.get(key), 0);
			if (npage <= 0) {
				continue;
			}

			String[] sp = key.split("_");
			String idx = sp[sp.length - 1];
			String relaz = null;
			if (map_relazioni.containsKey("relation_" + idx)) {
				relaz = map_relazioni.get("relation_" + idx);
			}
			if (relaz == null) {
				continue;
			}

			Relazione rel = pdcRelazione.getRelazioneAnnidata(relaz);
			if (rel == null || rel instanceof Relazione1_1) {
				continue;
			}

			Lista l = rel.getLista();
			int npages = l.getPageCount();
			if (npage > npages) {
				npage = npages;
			}
			if (npage == 0) {
				npage = 1;
			}

			l.setCurrPage(npage);
		}
		return true;
	}

	protected boolean caricaRelazioneManualmente(PDC pdc, Map<String, String> map_relazioni, Map<String, String> map_rowPerPage,
			Map<String, String> map_currentPage) throws Exception {
		return true;
	}

	/**
	 * Questo metodo serve per sapere se può essere effettuata la pdc.load()
	 * sull'oggetto pdc che viene passato. Questa funziona viene chiamata nella
	 * {@link #createXMLFragment()} dopo che il pdc è popolato con i dati ricevuti
	 * dalla richiesta http.<br/>
	 * Il comportamento di default è quello di caricare ogni pdc. Se si vuole
	 * cambiare basta sovrascrivere questo metodo ed controllare i vari valori del
	 * pdc secondo la logica voluta.
	 *
	 * @param pdcToLoad
	 *          è l'oggetto da esaminare che vuole essere caricato
	 *
	 * @return True nel caso si può procedere con il caricamento del pdc
	 *         passato.<br/>
	 *         False nel caso non devo caricare quel pdc
	 */
	protected boolean possoCaricarePdc(PDC pdcToLoad) {
		return true;
	}

	/**
	 * Questo metodo viene invocato per ogni nome di relazione (dopo la
	 * {@link filtraRelazioni}}) che si intende abilitare. Serve per esprimere se
	 * quella attualmente passata deve essere abilitata o si preferisce di no.
	 *
	 * @param nomeRelazione
	 *          è il nome della relazione in esame che si intende
	 *          abilitare
	 *
	 * @return True nel caso si vuole abilitare la relazione con il nome
	 *         passata.<br/>
	 *         False se non si vuole abilitarla
	 */
	protected boolean possoAbilitareRelazione(String nomeRelazione) {
		return true;
	}

	/**
	 * Funziona invocata quando si vuole controllare tutta la lista di relazioni,
	 * già filtrata dal metodo precedente. Così si può aggiungere anche delle
	 * relazioni che mi interessano particolarmente
	 */
	protected void esaminaRelazioni(Map<String, String> map_relazioni, Map<String, String> map_rowPerPage,
			Map<String, String> map_currentPage) {
	}

	@Override
	public EnumTipoLista getTipoLista() {
		return EnumTipoLista.LISTA;
	}
}
