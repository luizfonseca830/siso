/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.utils;

import br.com.siso.web.faces.constants.PagesUrl;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ioliveira
 */
public class FaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext facesContext = event.getFacesContext();
        HttpServletRequest origRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String currentPage = origRequest.getRequestURL().toString();
        boolean isLoginPage = currentPage.contains(PagesUrl.URL_LOGIN);
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute(Shareds.USER_TAG);
        if (!isLoginPage && (currentUser == null || currentUser.equals(""))) {
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
            navigationHandler.handleNavigation(facesContext, null, "loginPage");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // metodo nao usado
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
