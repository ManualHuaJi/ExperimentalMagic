package mhj.expmm.common.lib.research;

/**
 * @Author: ManualHuaJi
 */
public class OnetimeCaller {
    public Runnable call;

    public OnetimeCaller(final Runnable run) {
        this.call = run;
    }

    public void call() {
        if (this.call != null) {
            this.call.run();
        }
        this.call = null;
    }
}
