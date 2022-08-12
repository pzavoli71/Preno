package enumerati;

import sm.ciscoop.enumerati.Enumerato;
import sm.ciscoop.stdlibs.baseutils.struct.EnumConverter;
import sm.ciscoop.stdlibs.baseutils.struct.ReverseEnumMap;

public class EnumAR extends Enumerato {

	private static final long	 serialVersionUID	= 1L;

	public static final String NOME							= "AR";

	public enum EnAR implements EnumConverter<String> {
		A("1", "Sola andata"),
		R("2", "Solo ritorno"),
		T("3", "Tutti e due"),
		;

		private static ReverseEnumMap<String, EnAR>	map	= new ReverseEnumMap<String, EnAR>(EnAR.class);
		private final String																		value;
		private final String																		desc;

		private EnAR(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String getChiave() {
			return this.value;
		}

		@Override
		public String getDesc() {
			return desc;
		}

		public static EnAR valueOf(int valore) {
			return EnAR.map.get(String.valueOf(valore));
		}

	}

	@Override
	public String getNome() {
		return EnumAR.NOME;
	}

	@Override
	public void creaEnumerato() throws Exception {
		for (EnAR en : EnAR.values()) {
			add(en.getDesc(), en.getChiave());
		}
	}
}