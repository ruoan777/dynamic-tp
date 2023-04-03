package com.dtp.core.support;

import com.dtp.common.em.NotifyItemEnum;
import com.dtp.common.entity.NotifyItem;
import com.dtp.core.notify.capture.CapturedDtpExecutor;
import com.dtp.core.thread.DtpExecutor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executor wrapper
 *
 * @author yanhom
 * @since 1.0.3
 **/
@Data
public class ExecutorWrapper {

    private String threadPoolName;

    private String threadPoolAliasName;

    private ExecutorAdapter<?> executor;

    /**
     * Notify items, see {@link NotifyItemEnum}.
     */
    private List<NotifyItem> notifyItems;

    /**
     * Notify platform ids.
     */
    private List<String> platformIds;

    private boolean notifyEnabled = true;

    public ExecutorWrapper(DtpExecutor executor) {
        this.threadPoolName = executor.getThreadPoolName();
        this.executor = executor;
        this.notifyItems = executor.getNotifyItems();
        this.notifyEnabled = executor.isNotifyEnabled();
    }

    public ExecutorWrapper(String threadPoolName, Executor executor) {
        this.threadPoolName = threadPoolName;
        if (executor instanceof ThreadPoolExecutor) {
            this.executor = new ThreadPoolExecutorAdapter((ThreadPoolExecutor) executor);
        } else if (executor instanceof ExecutorAdapter<?>) {
            this.executor = (ExecutorAdapter<?>) executor;
        } else {
            throw new IllegalArgumentException("unsupported Executor type !");
        }
        this.notifyItems = NotifyItem.getSimpleNotifyItems();
    }

    private ExecutorWrapper(CapturedDtpExecutor captureDtpExecutor) {
        this.threadPoolName = captureDtpExecutor.getThreadPoolName();
        this.threadPoolAliasName = captureDtpExecutor.getThreadPoolAliasName();
        this.executor = captureDtpExecutor;
        this.notifyItems = captureDtpExecutor.getOriginal().getNotifyItems();
        this.notifyEnabled = captureDtpExecutor.getOriginal().isNotifyEnabled();
    }

    public static ExecutorWrapper of(DtpExecutor executor) {
        return new ExecutorWrapper(executor);
    }

    /**
     * used just for {@link com.dtp.core.context.BaseNotifyCtx}
     */
    public static ExecutorWrapper replaceExecutorWithCapturedDtpExecutor(ExecutorWrapper wrapper) {
        if (!(wrapper.getExecutor() instanceof DtpExecutor)) {
            return wrapper;
        }
        DtpExecutor executor = (DtpExecutor) wrapper.getExecutor();
        CapturedDtpExecutor captureDtpExecutor = new CapturedDtpExecutor(executor);
        return new ExecutorWrapper(captureDtpExecutor);
    }
}
