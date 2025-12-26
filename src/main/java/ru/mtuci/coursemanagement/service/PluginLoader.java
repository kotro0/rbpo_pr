package ru.mtuci.coursemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Plugin loading functionality removed due to RCE vulnerability.
 * Loading and executing code from external URLs poses a critical security risk.
 */
@Slf4j
@Component
public class PluginLoader {
    // Plugin loading functionality removed for security reasons
    public void tryLoad() {
        log.debug("Plugin loading is disabled for security reasons");
    }
}
