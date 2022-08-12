package sm.ciscoop.preno.batch;


import sm.ciscoop.preno.utils.AppProperties;
import sm.ciscoop.util.batch.StdBatch;


public abstract class AppBatch extends StdBatch {

	@Override
	protected void initBatch() throws Exception {
		getAppProperties();
		super.initBatch();
	}

	public AppProperties getAppProperties() {
		return AppProperties.getInstance();
	}
}
