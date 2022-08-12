package sm.ciscoop.preno.utils;


import sm.ciscoop.jbb.JBB;
import sm.ciscoop.pdc.Attributo;
import sm.ciscoop.stdlibs.db.GenericDBConn;
import sm.ciscoop.transportreport.configurator.StdReportConfigurator;


public class AppReportConfigurator extends StdReportConfigurator {

	private static final long serialVersionUID = -6334186369673763455L;

	private JBB								_jbb;

	public JBB getJBB() {
		return _jbb;
	}

	public AppReportConfigurator(GenericDBConn db) throws Exception {
		super(db);
	}

	public AppReportConfigurator(JBB db) throws Exception {
		super(GenericDBConn.getInstance(db.getCurrentPool(), db.getConnection()));
		_jbb = db;
	}

	public void addParameter(Attributo a) throws Exception {
		addParameter(a.getName(), a.getObjValue());
	}
}
