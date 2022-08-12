package sm.ciscoop.preno.hic.masks;


import sm.ciscoop.pdc.PDC;
import sm.ciscoop.servlet.StampeMask;
import sm.ciscoop.transportreport.configurator.StdReportConfigurator;


public abstract class AppStampeMask<P extends PDC, R extends StdReportConfigurator> extends StampeMask<P, R>
    implements IAppMask {

  private static final long serialVersionUID = 1L;

}
