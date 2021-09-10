package com.my.web.command;

import com.my.Path;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * View settings command.
 *
 * @author
 */
public class ViewSettingsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);
    private static final long serialVersionUID = -3178474466013689613L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }

}