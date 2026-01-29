package me.forty2.watloo.listener;

import me.forty2.watloo.tasks.UwOpenapiTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartListener {

    @Autowired
    private UwOpenapiTask uwOpenapiTask;

    @EventListener(AppStartListener.class)
    public void initData() {
        uwOpenapiTask.syncTerms();
    }
}
