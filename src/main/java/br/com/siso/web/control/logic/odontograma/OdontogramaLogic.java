/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.odontograma;

import br.com.siso.model.daos.TbservicofaceFacade;
import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbdente;
import br.com.siso.model.entities.Tbprocedimento;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbservicoface;
import br.com.siso.model.entities.Tbtipostatusprocedimento;
import br.com.siso.web.control.logic.dente.DenteLogic;
import br.com.siso.web.control.logic.procedimento.ProcedimentoLogic;
import br.com.siso.web.control.module.AbstractModuleCore;
import br.com.siso.web.faces.constants.StatusSiso;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.overlaypanel.OverlayPanel;

/**
 *
 * @author ioliveira
 */
@Stateless
public class OdontogramaLogic extends AbstractModuleCore {

    @EJB
    private TbservicofaceFacade tbservicofaceFacade;
    @EJB
    private DenteLogic denteLogic;
    @EJB
    private ProcedimentoLogic procedimentoLogic;

    /**
     * metodo utilizado para gerar odontograma adulto.
     *
     * @return htmlPanelDrig
     */
    public HtmlPanelGrid montarOdontogramaAdulto() {
        HtmlPanelGrid panelDenteAdulto = new HtmlPanelGrid();
        panelDenteAdulto.setColumns(16);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage graphicImageRaiz;
        GraphicImage graphicImageCervical;
        GraphicImage graphicImageDistal;
        GraphicImage graphicImageOclusal;
        GraphicImage graphicImageMesial;
        GraphicImage graphicImageVestibular;
        GraphicImage graphicImagePalatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 18 ao 11
        for (int i = 18; i >= 11; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 21 ao 28
        for (int i = 21; i <= 28; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        for (int i = 18; i >= 11; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            graphicImageRaiz = new GraphicImage();
            graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
            graphicImageRaiz.setTitle("Raiz do Dente");
            graphicImageRaiz.setStyle("border: 0px;");
            graphicImageRaiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idRaizParam" + i);

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(graphicImageRaiz);
            panelGroup.getChildren().add(commandLinkRaiz);
            panelGroup.getChildren().add(verbatimRaiz);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            graphicImageCervical = new GraphicImage();
            graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
            graphicImageCervical.setTitle("Face Cervical");
            graphicImageCervical.setStyle("border: 0px;");
            graphicImageCervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(graphicImageCervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            graphicImageVestibular = new GraphicImage();
            graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
            graphicImageVestibular.setTitle("Face Vestibular");
            graphicImageVestibular.setStyle("border: 0px;");
            graphicImageVestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(graphicImageVestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            graphicImageDistal = new GraphicImage();
            graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
            graphicImageDistal.setTitle("Face Distal");
            graphicImageDistal.setStyle("border: 0px;");
            graphicImageDistal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(graphicImageDistal);
            panelGroup.getChildren().add(commandLinkDistal);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            graphicImageOclusal = new GraphicImage();
            graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
            graphicImageOclusal.setTitle("Face Oclusal");
            graphicImageOclusal.setStyle("border: 0px;");
            graphicImageOclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(graphicImageOclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            graphicImageMesial = new GraphicImage();
            graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
            graphicImageMesial.setTitle("Face Mesial");
            graphicImageMesial.setStyle("border: 0px;");
            graphicImageMesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(graphicImageMesial);
            panelGroup.getChildren().add(commandLinkMesial);
            panelGroup.getChildren().add(verbatimMesial);

            // Montar a Face Palatina            
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            graphicImagePalatina = new GraphicImage();
            graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
            graphicImagePalatina.setTitle("Palatina");
            graphicImagePalatina.setStyle("border: 0px;");
            graphicImagePalatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(graphicImagePalatina);
            panelGroup.getChildren().add(commandLinkPalatina);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        for (int i = 21; i <= 28; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            graphicImageRaiz = new GraphicImage();
            graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
            graphicImageRaiz.setTitle("Raiz do Dente");
            graphicImageRaiz.setStyle("border: 0px;");
            graphicImageRaiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(graphicImageRaiz);
            panelGroup.getChildren().add(commandLinkRaiz);
            panelGroup.getChildren().add(verbatimRaiz);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            graphicImageCervical = new GraphicImage();
            graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
            graphicImageCervical.setTitle("Face Cervical");
            graphicImageCervical.setStyle("border: 0px;");
            graphicImageCervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(graphicImageCervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            graphicImageVestibular = new GraphicImage();
            graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
            graphicImageVestibular.setTitle("Face Vestibular");
            graphicImageVestibular.setStyle("border: 0px;");
            graphicImageVestibular.setId("_idVestiblarInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(graphicImageVestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            graphicImageMesial = new GraphicImage();
            graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
            graphicImageMesial.setTitle("Face Mesial");
            graphicImageMesial.setStyle("border: 0px;");
            graphicImageMesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(graphicImageMesial);
            panelGroup.getChildren().add(commandLinkMesial);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            graphicImageOclusal = new GraphicImage();
            graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
            graphicImageOclusal.setTitle("Face Oclusal");
            graphicImageOclusal.setStyle("border: 0px;");
            graphicImageOclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(graphicImageOclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            graphicImageDistal = new GraphicImage();
            graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
            graphicImageDistal.setTitle("Face Distal");
            graphicImageDistal.setStyle("border: 0px;");
            graphicImageDistal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(graphicImageDistal);
            panelGroup.getChildren().add(commandLinkDistal);
            panelGroup.getChildren().add(verbatimDistal);

            // Montar a Face Palatina
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            graphicImagePalatina = new GraphicImage();
            graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
            graphicImagePalatina.setTitle("Palatina");
            graphicImagePalatina.setStyle("border: 0px;");
            graphicImagePalatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(graphicImagePalatina);
            panelGroup.getChildren().add(commandLinkPalatina);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        for (int i = 48; i >= 41; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            graphicImageVestibular = new GraphicImage();
            graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
            graphicImageVestibular.setTitle("Face Vestibular");
            graphicImageVestibular.setStyle("border: 0px;");
            graphicImageVestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(graphicImageVestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            graphicImageDistal = new GraphicImage();
            graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
            graphicImageDistal.setTitle("Face Distal");
            graphicImageDistal.setStyle("border: 0px;");
            graphicImageDistal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(graphicImageDistal);
            panelGroup.getChildren().add(commandLinkDistal);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            graphicImageOclusal = new GraphicImage();
            graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
            graphicImageOclusal.setTitle("Face Oclusal");
            graphicImageOclusal.setStyle("border: 0px;");
            graphicImageOclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(graphicImageOclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            graphicImageMesial = new GraphicImage();
            graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
            graphicImageMesial.setTitle("Face Mesial");
            graphicImageMesial.setStyle("border: 0px;");
            graphicImageMesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(graphicImageMesial);
            panelGroup.getChildren().add(commandLinkMesial);
            panelGroup.getChildren().add(verbatimMesial);

            // Montar a Face Palatina            
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            graphicImagePalatina = new GraphicImage();
            graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
            graphicImagePalatina.setTitle("Palatina");
            graphicImagePalatina.setStyle("border: 0px;");
            graphicImagePalatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(graphicImagePalatina);
            panelGroup.getChildren().add(commandLinkPalatina);
            panelGroup.getChildren().add(verbatimPalatina);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            graphicImageCervical = new GraphicImage();
            graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
            graphicImageCervical.setTitle("Face Cervical");
            graphicImageCervical.setStyle("border: 0px;");
            graphicImageCervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(graphicImageCervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            graphicImageRaiz = new GraphicImage();
            graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
            graphicImageRaiz.setTitle("Raiz do Dente");
            graphicImageRaiz.setStyle("border: 0px;");
            graphicImageRaiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(graphicImageRaiz);
            panelGroup.getChildren().add(commandLinkRaiz);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        for (int i = 31; i <= 38; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            graphicImageVestibular = new GraphicImage();
            graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
            graphicImageVestibular.setTitle("Face Vestibular");
            graphicImageVestibular.setStyle("border: 0px;");
            graphicImageVestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(graphicImageVestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            graphicImageMesial = new GraphicImage();
            graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
            graphicImageMesial.setTitle("Face Mesial");
            graphicImageMesial.setStyle("border: 0px;");
            graphicImageMesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(graphicImageMesial);
            panelGroup.getChildren().add(commandLinkMesial);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOcliusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            graphicImageOclusal = new GraphicImage();
            graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
            graphicImageOclusal.setTitle("Face Oclusal");
            graphicImageOclusal.setStyle("border: 0px;");
            graphicImageOclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(graphicImageOclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            graphicImageDistal = new GraphicImage();
            graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
            graphicImageDistal.setTitle("Face Distal");
            graphicImageDistal.setStyle("border: 0px;");
            graphicImageDistal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(graphicImageDistal);
            panelGroup.getChildren().add(commandLinkDistal);
            panelGroup.getChildren().add(verbatimDistal);

            // Montar a Face Palatina
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            graphicImagePalatina = new GraphicImage();
            graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
            graphicImagePalatina.setTitle("Palatina");
            graphicImagePalatina.setStyle("border: 0px;");
            graphicImagePalatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(graphicImagePalatina);
            panelGroup.getChildren().add(commandLinkPalatina);
            panelGroup.getChildren().add(verbatimPalatina);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            graphicImageCervical = new GraphicImage();
            graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
            graphicImageCervical.setTitle("Face Cervical");
            graphicImageCervical.setStyle("border: 0px;");
            graphicImageCervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(graphicImageCervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            graphicImageRaiz = new GraphicImage();
            graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
            graphicImageRaiz.setTitle("Raiz do Dente");
            graphicImageRaiz.setStyle("border: 0px;");
            graphicImageRaiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(graphicImageRaiz);
            panelGroup.getChildren().add(commandLinkRaiz);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        // Monta do dente 48 ao 41
        for (int i = 48; i >= 41; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 31 ao 38
        for (int i = 31; i <= 38; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        return panelDenteAdulto;

    }

    /**
     * metodo utilizado para gerar odontograma infantil.
     *
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid montarOdontogramaInfantil() {

        HtmlPanelGrid panelDenteInfantil = new HtmlPanelGrid();
        panelDenteInfantil.setColumns(10);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage raiz;
        GraphicImage cervical;
        GraphicImage distal;
        GraphicImage oclusal;
        GraphicImage mesial;
        GraphicImage vestibular;
        GraphicImage palatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 55 ao 51
        for (int i = 55; i >= 51; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 61 ao 65
        for (int i = 61; i <= 65; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        for (int i = 55; i >= 51; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            raiz = new GraphicImage();
            raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
            raiz.setTitle("Raiz do Dente");
            raiz.setStyle("border: 0px;");
            raiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idRaizParam" + i);

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(raiz);
            panelGroup.getChildren().add(commandLinkRaiz);
            panelGroup.getChildren().add(verbatimRaiz);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            cervical = new GraphicImage();
            cervical.setUrl("/resources/images/orcamento/cervical.png");
            cervical.setTitle("Face Cervical");
            cervical.setStyle("border: 0px;");
            cervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(cervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            vestibular = new GraphicImage();
            vestibular.setUrl("/resources/images/orcamento/vestibular.png");
            vestibular.setTitle("Face Vestibular");
            vestibular.setStyle("border: 0px;");
            vestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(vestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            distal = new GraphicImage();
            distal.setUrl("/resources/images/orcamento/distal.png");
            distal.setTitle("Face Distal");
            distal.setStyle("border: 0px;");
            distal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(distal);
            panelGroup.getChildren().add(commandLinkDistal);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            oclusal = new GraphicImage();
            oclusal.setUrl("/resources/images/orcamento/oclusal.png");
            oclusal.setTitle("Face Oclusal");
            oclusal.setStyle("border: 0px;");
            oclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(oclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            mesial = new GraphicImage();
            mesial.setUrl("/resources/images/orcamento/mesial.png");
            mesial.setTitle("Face Mesial");
            mesial.setStyle("border: 0px;");
            mesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(mesial);
            panelGroup.getChildren().add(commandLinkMesial);
            panelGroup.getChildren().add(verbatimMesial);

            // Montar a Face Palatina            
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            palatina = new GraphicImage();
            palatina.setUrl("/resources/images/orcamento/palatina.png");
            palatina.setTitle("Palatina");
            palatina.setStyle("border: 0px;");
            palatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(palatina);
            panelGroup.getChildren().add(commandLinkPalatina);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        for (int i = 61; i <= 65; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            raiz = new GraphicImage();
            raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
            raiz.setTitle("Raiz do Dente");
            raiz.setStyle("border: 0px;");
            raiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(raiz);
            panelGroup.getChildren().add(commandLinkRaiz);
            panelGroup.getChildren().add(verbatimRaiz);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            cervical = new GraphicImage();
            cervical.setUrl("/resources/images/orcamento/cervical.png");
            cervical.setTitle("Face Cervical");
            cervical.setStyle("border: 0px;");
            cervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(cervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            vestibular = new GraphicImage();
            vestibular.setUrl("/resources/images/orcamento/vestibular.png");
            vestibular.setTitle("Face Vestibular");
            vestibular.setStyle("border: 0px;");
            vestibular.setId("_idVestiblarInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(vestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            mesial = new GraphicImage();
            mesial.setUrl("/resources/images/orcamento/distal.png");
            mesial.setTitle("Face Mesial");
            mesial.setStyle("border: 0px;");
            mesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(mesial);
            panelGroup.getChildren().add(commandLinkMesial);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            oclusal = new GraphicImage();
            oclusal.setUrl("/resources/images/orcamento/oclusal.png");
            oclusal.setTitle("Face Oclusal");
            oclusal.setStyle("border: 0px;");
            oclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(oclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            distal = new GraphicImage();
            distal.setUrl("/resources/images/orcamento/mesial.png");
            distal.setTitle("Face Distal");
            distal.setStyle("border: 0px;");
            distal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(distal);
            panelGroup.getChildren().add(commandLinkDistal);
            panelGroup.getChildren().add(verbatimDistal);

            // Montar a Face Palatina
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            palatina = new GraphicImage();
            palatina.setUrl("/resources/images/orcamento/palatina.png");
            palatina.setTitle("Palatina");
            palatina.setStyle("border: 0px;");
            palatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(palatina);
            panelGroup.getChildren().add(commandLinkPalatina);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        for (int i = 85; i >= 81; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            vestibular = new GraphicImage();
            vestibular.setUrl("/resources/images/orcamento/vestibular.png");
            vestibular.setTitle("Face Vestibular");
            vestibular.setStyle("border: 0px;");
            vestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(vestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            distal = new GraphicImage();
            distal.setUrl("/resources/images/orcamento/distal.png");
            distal.setTitle("Face Distal");
            distal.setStyle("border: 0px;");
            distal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(distal);
            panelGroup.getChildren().add(commandLinkDistal);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            oclusal = new GraphicImage();
            oclusal.setUrl("/resources/images/orcamento/oclusal.png");
            oclusal.setTitle("Face Oclusal");
            oclusal.setStyle("border: 0px;");
            oclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(oclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            mesial = new GraphicImage();
            mesial.setUrl("/resources/images/orcamento/mesial.png");
            mesial.setTitle("Face Mesial");
            mesial.setStyle("border: 0px;");
            mesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(mesial);
            panelGroup.getChildren().add(commandLinkMesial);
            panelGroup.getChildren().add(verbatimMesial);

            // Montar a Face Palatina            
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            palatina = new GraphicImage();
            palatina.setUrl("/resources/images/orcamento/palatina.png");
            palatina.setTitle("Palatina");
            palatina.setStyle("border: 0px;");
            palatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(palatina);
            panelGroup.getChildren().add(commandLinkPalatina);
            panelGroup.getChildren().add(verbatimPalatina);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            cervical = new GraphicImage();
            cervical.setUrl("/resources/images/orcamento/cervical.png");
            cervical.setTitle("Face Cervical");
            cervical.setStyle("border: 0px;");
            cervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(cervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            raiz = new GraphicImage();
            raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
            raiz.setTitle("Raiz do Dente");
            raiz.setStyle("border: 0px;");
            raiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(raiz);
            panelGroup.getChildren().add(commandLinkRaiz);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        for (int i = 71; i <= 75; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // Montar a Face Vestibular
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            vestibular = new GraphicImage();
            vestibular.setUrl("/resources/images/orcamento/vestibular.png");
            vestibular.setTitle("Face Vestibular");
            vestibular.setStyle("border: 0px;");
            vestibular.setId("_idVestibularInicial" + i);

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            commandLinkVestibular.getChildren().add(paramVestibular);
            commandLinkVestibular.getChildren().add(vestibular);
            panelGroup.getChildren().add(commandLinkVestibular);
            panelGroup.getChildren().add(verbatimVestibular);

            // Montar a Face Mesial
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            mesial = new GraphicImage();
            mesial.setUrl("/resources/images/orcamento/distal.png");
            mesial.setTitle("Face Mesial");
            mesial.setStyle("border: 0px;");
            mesial.setId("_idMesialInicial" + i);

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);
            commandLinkMesial.getChildren().add(mesial);
            panelGroup.getChildren().add(commandLinkMesial);

            // Montar a Face Oclusal
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOcliusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            oclusal = new GraphicImage();
            oclusal.setUrl("/resources/images/orcamento/oclusal.png");
            oclusal.setTitle("Face Oclusal");
            oclusal.setStyle("border: 0px;");
            oclusal.setId("_idOclusalInicial" + i);

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);
            commandLinkOclusal.getChildren().add(oclusal);
            panelGroup.getChildren().add(commandLinkOclusal);

            // Montar a Face Distal
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            distal = new GraphicImage();
            distal.setUrl("/resources/images/orcamento/mesial.png");
            distal.setTitle("Face Distal");
            distal.setStyle("border: 0px;");
            distal.setId("_idDistalInicial" + i);

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            commandLinkDistal.getChildren().add(paramDistal);
            commandLinkDistal.getChildren().add(distal);
            panelGroup.getChildren().add(commandLinkDistal);
            panelGroup.getChildren().add(verbatimDistal);

            // Montar a Face Palatina
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            palatina = new GraphicImage();
            palatina.setUrl("/resources/images/orcamento/palatina.png");
            palatina.setTitle("Palatina");
            palatina.setStyle("border: 0px;");
            palatina.setId("_idPalatinaInicial" + i);

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            commandLinkPalatina.getChildren().add(paramPalatina);
            commandLinkPalatina.getChildren().add(palatina);
            panelGroup.getChildren().add(commandLinkPalatina);
            panelGroup.getChildren().add(verbatimPalatina);

            // Montar a Face Cervical
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            cervical = new GraphicImage();
            cervical.setUrl("/resources/images/orcamento/cervical.png");
            cervical.setTitle("Face Cervical");
            cervical.setStyle("border: 0px;");
            cervical.setId("_idCervicalInicial" + i);

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            commandLinkCervical.getChildren().add(paramCervical);
            commandLinkCervical.getChildren().add(cervical);
            panelGroup.getChildren().add(commandLinkCervical);

            panelGroup.getChildren().add(verbatimCervical);

            // Montar a Raiz do Dente
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            raiz = new GraphicImage();
            raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
            raiz.setTitle("Raiz do Dente");
            raiz.setStyle("border: 0px;");
            raiz.setId("_idRaizInicial" + i);

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);
            commandLinkRaiz.getChildren().add(raiz);
            panelGroup.getChildren().add(commandLinkRaiz);

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        // Monta do dente 85 ao 81
        for (int i = 85; i >= 81; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 71 ao 75
        for (int i = 71; i <= 75; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        return panelDenteInfantil;
    }

    /**
     * metodo para remontar Odontograma adulto com os procedimentos do cliente.
     *
     * @param panelOdontogramaAdulto
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaAdultoAtendimento(final HtmlPanelGrid panelOdontogramaAdulto,
            final List<Tbprocedimento> listTbprocedimentos) {

        HashMap mapRemontagem = new LinkedHashMap();

        for (final Tbprocedimento procedimento : listTbprocedimentos) {
            if (!mapRemontagem.containsKey(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()))) {
                mapRemontagem.put(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()), String.valueOf(procedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento()));
            }
        }

        panelOdontogramaAdulto.getChildren().clear();
        panelOdontogramaAdulto.setColumns(16);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage raiz;
        GraphicImage cervical;
        GraphicImage distal;
        GraphicImage oclusal;
        GraphicImage mesial;
        GraphicImage vestibular;
        GraphicImage palatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 18 ao 11
        for (int i = 18; i >= 11; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 21 ao 28
        for (int i = 21; i <= 28; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaAdulto.getChildren().add(outputtext);
        }

        for (int i = 18; i >= 11; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // ****************************  Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_trat.png");
                        break;
                }

                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkRaiz.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/distal.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/mesial.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                imagem.setTitle("Face Palatina");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkPalatina.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaAdulto.getChildren().add(panelGroup);

        }

        for (int i = 21; i <= 28; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_trat.png");
                        break;
                }

                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + String.valueOf(i));

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkRaiz.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/distal.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/mesial.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkPalatina.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaAdulto.getChildren().add(panelGroup);

        }

        for (int i = 48; i >= 41; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                imagem.setTitle("Face Vestibular");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkVestibular.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {

                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/distal.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/mesial.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkPalatina.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_trat.png");
                        break;
                }

                imagem.setTitle("Raiz do Dente");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkRaiz.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaAdulto.getChildren().add(panelGroup);

        }

        for (int i = 31; i <= 38; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/distal.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/mesial.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                imagem.setTitle("Face Palatina");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkPalatina.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage imagem = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_trat.png");
                        break;
                }

                imagem.setTitle("Raiz do Dente");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkRaiz.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaAdulto.getChildren().add(panelGroup);

        }

        // Monta do dente 48 ao 41
        for (int i = 48; i >= 41; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue("   " + i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 31 ao 38
        for (int i = 31; i <= 38; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue("  " + i);
            outputtext.setId("_idOutPuttext" + i);

            panelOdontogramaAdulto.getChildren().add(outputtext);
        }

        panelOdontogramaAdulto.processUpdates(FacesContext.getCurrentInstance());

        return panelOdontogramaAdulto;
    }

    /**
     * metodo para remontar Odontograma infantil com os procedimentos do
     * cliente.
     *
     * @param panelOdontogramaInfantil
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaInfantilAtendimento(final HtmlPanelGrid panelOdontogramaInfantil,
            final List<Tbprocedimento> listTbprocedimentos) {

        HashMap mapRemontagem = new LinkedHashMap();

        for (final Tbprocedimento procedimento : listTbprocedimentos) {
            if (!mapRemontagem.containsKey(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()))) {
                mapRemontagem.put(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()), String.valueOf(procedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento()));
            }
        }

        panelOdontogramaInfantil.getChildren().clear();
        panelOdontogramaInfantil.setColumns(10);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage raiz;
        GraphicImage cervical;
        GraphicImage distal;
        GraphicImage oclusal;
        GraphicImage mesial;
        GraphicImage vestibular;
        GraphicImage palatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 18 ao 11
        for (int i = 55; i >= 51; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 21 ao 28
        for (int i = 61; i <= 65; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue("     " + i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaInfantil.getChildren().add(outputtext);
        }

        for (int i = 55; i >= 51; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // ****************************  Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_trat.png");
                        break;
                }

                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkRaiz.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/distal.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/mesial.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                imagem.setTitle("Face Palatina");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkPalatina.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaInfantil.getChildren().add(panelGroup);

        }

        for (int i = 61; i <= 65; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/raiz_superior_trat.png");
                        break;
                }

                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + String.valueOf(i));

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkRaiz.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/distal.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/mesial.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkPalatina.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaInfantil.getChildren().add(panelGroup);

        }

        for (int i = 85; i >= 81; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                imagem.setTitle("Face Vestibular");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkVestibular.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {

                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/distal.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/mesial.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkPalatina.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_trat.png");
                        break;
                }

                imagem.setTitle("Raiz do Dente");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkRaiz.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaInfantil.getChildren().add(panelGroup);

        }

        for (int i = 71; i <= 75; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                vestibular = new GraphicImage();
                vestibular.setUrl("/resources/images/orcamento/vestibular.png");
                vestibular.setTitle("Face Vestibular");
                vestibular.setStyle("border: 0px;");
                vestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(vestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/vestibular_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkVestibular.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                mesial = new GraphicImage();
                mesial.setUrl("/resources/images/orcamento/distal.png");
                mesial.setTitle("Face Mesial");
                mesial.setStyle("border: 0px;");
                mesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(mesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/distal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/distal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkMesial.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                oclusal = new GraphicImage();
                oclusal.setUrl("/resources/images/orcamento/oclusal.png");
                oclusal.setTitle("Face Oclusal");
                oclusal.setStyle("border: 0px;");
                oclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(oclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/oclusal_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkOclusal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                distal = new GraphicImage();
                distal.setUrl("/resources/images/orcamento/mesial.png");
                distal.setTitle("Face Distal");
                distal.setStyle("border: 0px;");
                distal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(distal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/mesial_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkDistal.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                palatina = new GraphicImage();
                palatina.setUrl("/resources/images/orcamento/palatina.png");
                palatina.setTitle("Palatina");
                palatina.setStyle("border: 0px;");
                palatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(palatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage imagem = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/palatina_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/palatina_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/palatina_trat.png");
                        break;
                }

                imagem.setTitle("Face Palatina");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idPalatinaReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkPalatina.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                cervical = new GraphicImage();
                cervical.setUrl("/resources/images/orcamento/cervical.png");
                cervical.setTitle("Face Cervical");
                cervical.setStyle("border: 0px;");
                cervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(cervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();

                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL);

                switch (Integer.parseInt(status)) {
                    case 0:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                        break;
                    case 1:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_conc.png");
                        break;
                    case 2:
                        graphicImage.setUrl("/resources/images/orcamento/cervical_trat.png");
                        break;
                }

                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());

                commandLinkCervical.getChildren().add(graphicImage);
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                raiz = new GraphicImage();
                raiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                raiz.setTitle("Raiz do Dente");
                raiz.setStyle("border: 0px;");
                raiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(raiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage imagem = new GraphicImage();
                String status = (String) mapRemontagem.get(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ);

                switch (Integer.parseInt(status)) {
                    case 0:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                        break;
                    case 1:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_conc.png");
                        break;
                    case 2:
                        imagem.setUrl("/resources/images/orcamento/raiz_inferior_trat.png");
                        break;
                }

                imagem.setTitle("Raiz do Dente");
                imagem.setStyle("border: 0px;");
                imagem.setId("_idRaizReplace" + i);

                OverlayPanel overlayPanel = montarOverlayPanelAtendimento(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, imagem.getId());
                overlayPanel.setFor(imagem.getId());

                commandLinkRaiz.getChildren().add(imagem);
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelOdontogramaInfantil.getChildren().add(panelGroup);

        }

        // Monta do dente 85 ao 81
        for (int i = 85; i >= 81; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue("   " + i);
            outputtext.setId("_idOutputText" + i);

            panelOdontogramaInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 71 ao 75
        for (int i = 71; i <= 75; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue("  " + i);
            outputtext.setId("_idOutPuttext" + i);

            panelOdontogramaInfantil.getChildren().add(outputtext);
        }

        panelOdontogramaInfantil.processUpdates(FacesContext.getCurrentInstance());

        return panelOdontogramaInfantil;
    }

    /**
     * metodo utilizado para remontar odontograma para orcamento.
     *
     * @param panelDenteAdulto
     * @param boPopup
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaAdulto(HtmlPanelGrid panelDenteAdulto, boolean boPopup, final List<Tbprocedimento> listTbprocedimentos) {

        HashMap mapRemontagem = new HashMap();
        for (final Tbprocedimento procedimento : listTbprocedimentos) {
            if (!mapRemontagem.containsKey(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()))) {
                mapRemontagem.put(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()), String.valueOf(procedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento()));
            }
        }

        panelDenteAdulto.getChildren().clear();
        panelDenteAdulto.setColumns(16);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage graphicImageRaiz;
        GraphicImage graphicImageCervical;
        GraphicImage graphicImageDistal;
        GraphicImage graphicImageOclusal;
        GraphicImage graphicImageMesial;
        GraphicImage graphicImageVestibular;
        GraphicImage graphicImagePalatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 18 ao 11
        for (int i = 18; i >= 11; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 21 ao 28
        for (int i = 21; i <= 28; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        for (int i = 18; i >= 11; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");

            panelGroup.getChildren().add(verbatimAbreCenter);

            // ****************************  Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizSuperiorInicial" + String.valueOf(i));
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizSuperiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkRaiz);
                    commandLinkRaiz.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkCervical);
                    commandLinkCervical.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkVestibular);
                    commandLinkVestibular.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkDistal);
                    commandLinkDistal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkOclusal);
                    commandLinkOclusal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkMesial);
                    commandLinkMesial.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Face Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkPalatina);
                    commandLinkPalatina.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        for (int i = 21; i <= 28; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizSuperiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkRaiz);
                    commandLinkRaiz.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkCervical);
                    commandLinkCervical.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkVestibular);
                    commandLinkVestibular.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkMesial);
                    commandLinkMesial.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkOclusal);
                    commandLinkOclusal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + String.valueOf(i));
                commandLinkDistal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkDistal);
                    commandLinkDistal.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkPalatina);
                    commandLinkPalatina.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);

        }

        for (int i = 48; i >= 41; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkVestibular);
                    commandLinkVestibular.getChildren().add(overlayPanel);
                }
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {

                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkDistal);
                    commandLinkDistal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkOclusal);
                    commandLinkOclusal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkMesial);
                    commandLinkMesial.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkPalatina);
                    commandLinkPalatina.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkCervical);
                    commandLinkCervical.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizInferiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkRaiz);
                    commandLinkRaiz.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);
        }

        for (int i = 31; i <= 38; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkVestibular);
                    commandLinkVestibular.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkMesial);
                    commandLinkMesial.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkOclusal);
                    commandLinkOclusal.getParent().getChildren().add(overlayPanel);
                }
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkDistal);
                    commandLinkDistal.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkPalatina);
                    commandLinkPalatina.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkCervical);
                    commandLinkCervical.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                if (boPopup) {
                    OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                    overlayPanel.setFor(graphicImage.getId());
                    panelGroup.getChildren().add(commandLinkRaiz);
                    commandLinkRaiz.getParent().getChildren().add(overlayPanel);
                }
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteAdulto.getChildren().add(panelGroup);
        }

        // Monta do dente 48 ao 41
        for (int i = 48; i >= 41; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        // Monta do dente 31 ao 38
        for (int i = 31; i <= 38; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteAdulto.getChildren().add(outputtext);
        }

        panelDenteAdulto.processUpdates(FacesContext.getCurrentInstance());

        return panelDenteAdulto;
    }

    /**
     * metodo utilizado para remontar odontograma infantil.
     *
     * @param panelDenteInfantil
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaInfantil(HtmlPanelGrid panelDenteInfantil, final List<Tbprocedimento> listTbprocedimentos) {

        HashMap mapRemontagem = new HashMap();
        for (final Tbprocedimento procedimento : listTbprocedimentos) {
            if (!mapRemontagem.containsKey(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()))) {
                mapRemontagem.put(String.valueOf(procedimento.getIddente().getNrposicao()) + ";" + String.valueOf(procedimento.getInfacedente()), String.valueOf(procedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento()));
            }
        }

        panelDenteInfantil.getChildren().clear();
        panelDenteInfantil.setColumns(10);

        HtmlPanelGroup panelGroup;

        HtmlOutputText outputtext;

        HtmlCommandLink commandLinkRaiz;
        HtmlCommandLink commandLinkCervical;
        HtmlCommandLink commandLinkVestibular;
        HtmlCommandLink commandLinkDistal;
        HtmlCommandLink commandLinkOclusal;
        HtmlCommandLink commandLinkMesial;
        HtmlCommandLink commandLinkPalatina;

        GraphicImage graphicImageRaiz;
        GraphicImage graphicImageCervical;
        GraphicImage graphicImageDistal;
        GraphicImage graphicImageOclusal;
        GraphicImage graphicImageMesial;
        GraphicImage graphicImageVestibular;
        GraphicImage graphicImagePalatina;

        UIParameter paramRaiz;
        UIParameter paramCervical;
        UIParameter paramVestibular;
        UIParameter paramDistal;
        UIParameter paramOclusal;
        UIParameter paramMesial;
        UIParameter paramPalatina;

        HtmlOutputText verbatimRaiz;
        HtmlOutputText verbatimPalatina;
        HtmlOutputText verbatimCervical;
        HtmlOutputText verbatimVestibular;
        HtmlOutputText verbatimDistal;
        HtmlOutputText verbatimMesial;

        HtmlOutputText verbatimAbreCenter;
        HtmlOutputText verbatimFechaCenter;

        // Monta do dente 55 ao 51
        for (int i = 55; i >= 51; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 61 ao 65
        for (int i = 61; i <= 65; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        for (int i = 55; i >= 51; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");

            panelGroup.getChildren().add(verbatimAbreCenter);

            // ****************************  Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizSuperiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Face Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        for (int i = 61; i <= 65; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_superior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizSuperiorInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizSuperiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimRaiz = new HtmlOutputText();
            verbatimRaiz.getAttributes().put("escape", Boolean.FALSE);
            verbatimRaiz.setValue("<BR>");

            panelGroup.getChildren().add(verbatimRaiz);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + String.valueOf(i));
                commandLinkDistal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        for (int i = 85; i >= 81; i--) {
            Class[] actionevent = new Class[]{ActionEvent.class};

            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {

                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/distal.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            verbatimMesial = new HtmlOutputText();
            verbatimMesial.getAttributes().put("escape", Boolean.FALSE);
            verbatimMesial.setValue("<BR>");

            panelGroup.getChildren().add(verbatimMesial);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizInferiorInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizInferiorReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);
        }

        for (int i = 71; i <= 75; i++) {
            Class[] actionevent = new Class[]{ActionEvent.class};
            panelGroup = new HtmlPanelGroup();

            verbatimAbreCenter = new HtmlOutputText();
            verbatimAbreCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimAbreCenter.setValue("<CENTER>");
            panelGroup.getChildren().add(verbatimAbreCenter);

            // **************************** Montar a Face Vestibular ****************************
            commandLinkVestibular = new HtmlCommandLink();
            commandLinkVestibular.setId("_idVestibularCommandLink" + i);
            commandLinkVestibular.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addVestibular}", actionevent));

            paramVestibular = new UIParameter();
            paramVestibular.setName("idDente");
            paramVestibular.setValue(i);
            paramVestibular.setId("_idParamVestibular" + i);

            commandLinkVestibular.getChildren().add(paramVestibular);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                graphicImageVestibular = new GraphicImage();
                graphicImageVestibular.setUrl("/resources/images/orcamento/vestibular.png");
                graphicImageVestibular.setTitle("Face Vestibular");
                graphicImageVestibular.setStyle("border: 0px;");
                graphicImageVestibular.setId("_idVestibularInicial" + i);
                commandLinkVestibular.getChildren().add(graphicImageVestibular);
                panelGroup.getChildren().add(commandLinkVestibular);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/vestibular_proc.png");
                graphicImage.setTitle("Face Vestibular");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idVestibularReplace" + i);
                commandLinkVestibular.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkVestibular);
                commandLinkVestibular.getParent().getChildren().add(overlayPanel);
            }

            verbatimVestibular = new HtmlOutputText();
            verbatimVestibular.getAttributes().put("escape", Boolean.FALSE);
            verbatimVestibular.setValue("<BR>");

            panelGroup.getChildren().add(verbatimVestibular);

            // **************************** Montar a Face Mesial ****************************
            commandLinkMesial = new HtmlCommandLink();
            commandLinkMesial.setId("_idMesialCommandLink" + i);
            commandLinkMesial.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addMesial}", actionevent));

            paramMesial = new UIParameter();
            paramMesial.setName("idDente");
            paramMesial.setValue(i);
            paramMesial.setId("_idParamMesial" + i);

            commandLinkMesial.getChildren().add(paramMesial);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_MESIAL)) {
                graphicImageMesial = new GraphicImage();
                graphicImageMesial.setUrl("/resources/images/orcamento/distal.png");
                graphicImageMesial.setTitle("Face Mesial");
                graphicImageMesial.setStyle("border: 0px;");
                graphicImageMesial.setId("_idMesialInicial" + i);
                commandLinkMesial.getChildren().add(graphicImageMesial);
                panelGroup.getChildren().add(commandLinkMesial);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/distal_proc.png");
                graphicImage.setTitle("Face Mesial");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idMesialReplace" + i);
                commandLinkMesial.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkMesial);
                commandLinkMesial.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Oclusal ****************************
            commandLinkOclusal = new HtmlCommandLink();
            commandLinkOclusal.setId("_idOclusalCommandLink" + i);
            commandLinkOclusal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addOclusal}", actionevent));

            paramOclusal = new UIParameter();
            paramOclusal.setName("idDente");
            paramOclusal.setValue(i);
            paramOclusal.setId("_idParamOclusal" + i);

            commandLinkOclusal.getChildren().add(paramOclusal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                graphicImageOclusal = new GraphicImage();
                graphicImageOclusal.setUrl("/resources/images/orcamento/oclusal.png");
                graphicImageOclusal.setTitle("Face Oclusal");
                graphicImageOclusal.setStyle("border: 0px;");
                graphicImageOclusal.setId("_idOclusalInicial" + i);
                commandLinkOclusal.getChildren().add(graphicImageOclusal);
                panelGroup.getChildren().add(commandLinkOclusal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/oclusal_proc.png");
                graphicImage.setTitle("Face Oclusal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idOclusalReplace" + i);
                commandLinkOclusal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkOclusal);
                commandLinkOclusal.getParent().getChildren().add(overlayPanel);
            }

            // **************************** Montar a Face Distal ****************************
            commandLinkDistal = new HtmlCommandLink();
            commandLinkDistal.setId("_idDistalCommandLink" + i);
            commandLinkDistal.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addDistal}", actionevent));

            paramDistal = new UIParameter();
            paramDistal.setName("idDente");
            paramDistal.setValue(i);
            paramDistal.setId("_idParamDistal" + i);

            commandLinkDistal.getChildren().add(paramDistal);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_DISTAL)) {
                graphicImageDistal = new GraphicImage();
                graphicImageDistal.setUrl("/resources/images/orcamento/mesial.png");
                graphicImageDistal.setTitle("Face Distal");
                graphicImageDistal.setStyle("border: 0px;");
                graphicImageDistal.setId("_idDistalInicial" + i);
                commandLinkDistal.getChildren().add(graphicImageDistal);
                panelGroup.getChildren().add(commandLinkDistal);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/mesial_proc.png");
                graphicImage.setTitle("Face Distal");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idDistalReplace" + i);
                commandLinkDistal.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkDistal);
                commandLinkDistal.getParent().getChildren().add(overlayPanel);
            }

            verbatimDistal = new HtmlOutputText();
            verbatimDistal.getAttributes().put("escape", Boolean.FALSE);
            verbatimDistal.setValue("<BR>");

            panelGroup.getChildren().add(verbatimDistal);

            // **************************** Montar a Face Palatina ****************************
            commandLinkPalatina = new HtmlCommandLink();
            commandLinkPalatina.setId("_idPalatinaCommandLink" + i);
            commandLinkPalatina.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addPalatina}", actionevent));

            paramPalatina = new UIParameter();
            paramPalatina.setName("idDente");
            paramPalatina.setValue(i);
            paramPalatina.setId("_idParamPalatina" + i);

            commandLinkPalatina.getChildren().add(paramPalatina);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_PALATINA)) {
                graphicImagePalatina = new GraphicImage();
                graphicImagePalatina.setUrl("/resources/images/orcamento/palatina.png");
                graphicImagePalatina.setTitle("Palatina");
                graphicImagePalatina.setStyle("border: 0px;");
                graphicImagePalatina.setId("_idPalatinaInicial" + i);
                commandLinkPalatina.getChildren().add(graphicImagePalatina);
                panelGroup.getChildren().add(commandLinkPalatina);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/palatina_proc.png");
                graphicImage.setTitle("Face Palatina");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idPalatinaReplace" + i);
                commandLinkPalatina.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkPalatina);
                commandLinkPalatina.getParent().getChildren().add(overlayPanel);
            }

            verbatimPalatina = new HtmlOutputText();
            verbatimPalatina.getAttributes().put("escape", Boolean.FALSE);
            verbatimPalatina.setValue("<BR>");

            panelGroup.getChildren().add(verbatimPalatina);

            // **************************** Montar a Face Cervical ****************************
            commandLinkCervical = new HtmlCommandLink();
            commandLinkCervical.setId("_idCervicalCommandLink" + i);
            commandLinkCervical.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addCervical}", actionevent));

            paramCervical = new UIParameter();
            paramCervical.setName("idDente");
            paramCervical.setValue(i);
            paramCervical.setId("_idParamCervical" + i);

            commandLinkCervical.getChildren().add(paramCervical);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                graphicImageCervical = new GraphicImage();
                graphicImageCervical.setUrl("/resources/images/orcamento/cervical.png");
                graphicImageCervical.setTitle("Face Cervical");
                graphicImageCervical.setStyle("border: 0px;");
                graphicImageCervical.setId("_idCervicalInicial" + i);
                commandLinkCervical.getChildren().add(graphicImageCervical);
                panelGroup.getChildren().add(commandLinkCervical);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/cervical_proc.png");
                graphicImage.setTitle("Face Cervical");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idCervicalReplace" + i);
                commandLinkCervical.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkCervical);
                commandLinkCervical.getParent().getChildren().add(overlayPanel);
            }

            verbatimCervical = new HtmlOutputText();
            verbatimCervical.getAttributes().put("escape", Boolean.FALSE);
            verbatimCervical.setValue("<BR>");

            panelGroup.getChildren().add(verbatimCervical);

            // **************************** Montar a Raiz do Dente ****************************
            commandLinkRaiz = new HtmlCommandLink();
            commandLinkRaiz.setId("_idRaizCommandLink" + i);
            commandLinkRaiz.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                    "#{atendimentoBean.addRaiz}", actionevent));

            paramRaiz = new UIParameter();
            paramRaiz.setName("idDente");
            paramRaiz.setValue(i);
            paramRaiz.setId("_idParamRaiz" + i);

            commandLinkRaiz.getChildren().add(paramRaiz);

            if (!mapRemontagem.containsKey(String.valueOf(i) + ";" + StatusSiso.IN_FACE_DENTE_RAIZ)) {
                graphicImageRaiz = new GraphicImage();
                graphicImageRaiz.setUrl("/resources/images/orcamento/raiz_inferior.png");
                graphicImageRaiz.setTitle("Raiz do Dente");
                graphicImageRaiz.setStyle("border: 0px;");
                graphicImageRaiz.setId("_idRaizInicial" + i);
                commandLinkRaiz.getChildren().add(graphicImageRaiz);
                panelGroup.getChildren().add(commandLinkRaiz);
            } else {
                GraphicImage graphicImage = new GraphicImage();
                graphicImage.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                graphicImage.setTitle("Raiz do Dente");
                graphicImage.setStyle("border: 0px;");
                graphicImage.setId("_idRaizReplace" + i);
                commandLinkRaiz.getChildren().add(graphicImage);

                OverlayPanel overlayPanel = montarOverlayPanel(String.valueOf(i), StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", graphicImage.getId());
                overlayPanel.setFor(graphicImage.getId());
                panelGroup.getChildren().add(commandLinkRaiz);
                commandLinkRaiz.getParent().getChildren().add(overlayPanel);
            }

            verbatimFechaCenter = new HtmlOutputText();
            verbatimFechaCenter.getAttributes().put("escape", Boolean.FALSE);
            verbatimFechaCenter.setValue("</CENTER>");
            panelGroup.getChildren().add(verbatimFechaCenter);

            panelDenteInfantil.getChildren().add(panelGroup);

        }

        // Monta do dente 85 ao 81
        for (int i = 85; i >= 81; i--) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputtext" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        // Monta do dente 71 ao 75
        for (int i = 71; i <= 75; i++) {
            outputtext = new HtmlOutputText();
            outputtext.setStyleClass("text-bold");
            outputtext.setValue(i);
            outputtext.setId("_idOutputText" + i);

            panelDenteInfantil.getChildren().add(outputtext);
        }

        panelDenteInfantil.processUpdates(FacesContext.getCurrentInstance());

        return panelDenteInfantil;
    }

    /**
     * metodo para adicionar procedimento na raiz.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarRaiz(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_RAIZ, tbservico)) {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            try {
                Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {
                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_RAIZ, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);
                        Integer posicaodente = Integer.parseInt(nrDente);

                        if ((posicaodente >= 11 && posicaodente <= 28)
                                || (posicaodente >= 51 && posicaodente <= 65)) {
                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/raiz_superior_proc.png");
                            imagem.setTitle("Raiz do Dente");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idRaizSuperiorReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na raiz do dente " + nrDente + "!");

                        } else {
                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/raiz_inferior_proc.png");
                            imagem.setTitle("Raiz do Dente");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idRaizInferiorReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_RAIZ, "adicionarPopupRaiz", "replacePopupRaiz", "removePopupRaiz", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na raiz do dente " + nrDente + "!");
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(" ERRO ADICIONAR RAIZ: " + e.getMessage());
                AbstractFacesContextUtils.addMessageWarn("Dente " + nrDente + " não cadastrado no sistema!");
            }
        }

        return result;
    }

    /**
     * metodo para remover procedimento do dente raiz.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupRaiz(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {
            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_RAIZ)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {
                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_RAIZ)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 28)
                            || (posicaodente >= 51 && posicaodente <= 65)) {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_RAIZ);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/raiz_superior.png");
                                graphicImage.setTitle("Raiz do Dente");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idRaizSuperiorInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da raiz do dente " + nrDente + "!");
                    } else {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_RAIZ);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/raiz_inferior.png");
                                graphicImage.setTitle("Raiz do Dente");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idRaizInferiorInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da raiz do dente " + nrDente + "!");
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER RAIZ: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo para adicionar procedimento na Cervical.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarCervical(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_CERVICAL, tbservico)) {

            String nrDente = ((UIComponent) event.getComponent().getChildren()
                    .get(0)).getAttributes().get("value").toString();

            try {
                Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {
                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_CERVICAL, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);
                        result = true;

                        GraphicImage imagem = new GraphicImage();
                        imagem.setUrl("/resources/images/orcamento/cervical_proc.png");
                        imagem.setTitle("Face Cervical");
                        imagem.setStyle("border: 0px;");
                        imagem.setId("_idCervicalReplace" + nrDente);

                        OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_CERVICAL, "adicionarPopupCervical", "replacePopupCervical", "removePopupCervical", imagem.getId());
                        overlayPanel.setFor(imagem.getId());

                        event.getComponent().getChildren().set(1, imagem);
                        event.getComponent().getParent().getChildren().add(overlayPanel);
                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face cervical do dente " + nrDente + "!");
                    }
                }
            } catch (Exception e) {
                System.out.println(" ERRO ADICIONAR CERVICAL: " + e.getMessage());
                AbstractFacesContextUtils.addMessageWarn("Dente " + nrDente + " não cadastrado no sistema!");
            }
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face cervical do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true
     */
    public boolean removePopupCervical(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {

                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_CERVICAL)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 28)
                            || (posicaodente >= 51 && posicaodente <= 65)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_CERVICAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/cervical.png");
                                graphicImage.setTitle("Face Cervical");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idCervicalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face cervical do dente " + nrDente + "!");
                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_CERVICAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/cervical.png");
                                graphicImage.setTitle("Face Cervical");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idCervicalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face cervical do dente " + nrDente + "!");
                    }

                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER CERVICAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento!");
        }

        return result;
    }

    /**
     * metodo para adicionar procedimento na vestibular.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarVestibular(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_VESTIBULAR, tbservico)) {
                String nrDente = ((UIComponent) event.getComponent().getChildren()
                        .get(0)).getAttributes().get("value").toString();

                final Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {
                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_VESTIBULAR, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);
                        result = true;

                        GraphicImage imagem = new GraphicImage();
                        imagem.setUrl("/resources/images/orcamento/vestibular_proc.png");
                        imagem.setTitle("Face Vestibular");
                        imagem.setStyle("border: 0px;");
                        imagem.setId("_idVestibularReplace" + nrDente);

                        OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_VESTIBULAR, "adicionarPopupVestibular", "replacePopupVestibular", "removePopupVestibular", imagem.getId());
                        overlayPanel.setFor(imagem.getId());

                        event.getComponent().getChildren().set(1, imagem);
                        event.getComponent().getParent().getChildren().add(overlayPanel);
                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face vestibular do dente " + nrDente + "!");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(" ERRO ADICIONAR VESTIBULAR: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face vestibular do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupVestibular(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {

                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_VESTIBULAR)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 28)
                            || (posicaodente >= 51 && posicaodente <= 65)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/vestibular.png");
                                graphicImage.setTitle("Face Vestibular");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idVestibularInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face vestibular do dente " + nrDente + "!");
                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_VESTIBULAR);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(1).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/vestibular.png");
                                graphicImage.setTitle("Face Vestibular");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idVestibularInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face vestibular do dente " + nrDente + "!");
                    }

                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER VESTIBULAR: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento!");
        }

        return result;
    }

    /**
     * metodo para adicionar procedimento no Distal.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     *
     */
    public boolean adicionarDistal(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {
            if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_DISTAL, tbservico)) {
                String nrDente = ((UIComponent) event.getComponent().getChildren()
                        .get(0)).getAttributes().get("value").toString();

                final Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {

                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_DISTAL, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);

                        Integer posicaodente = Integer.parseInt(nrDente);

                        if ((posicaodente >= 11 && posicaodente <= 18)
                                || (posicaodente >= 41 && posicaodente <= 48)
                                || (posicaodente >= 51 && posicaodente <= 55)
                                || (posicaodente >= 81 && posicaodente <= 85)) {

                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/distal_proc.png");
                            imagem.setTitle("Face Distal");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idDistalReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face distal do dente " + nrDente + "!");
                        } else {

                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/mesial_proc.png");
                            imagem.setTitle("Face Distal");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idDistalReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_DISTAL, "adicionarPopupDistal", "replacePopupDistal", "removePopupDistal", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face distal do dente " + nrDente + "!");
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(" ERRO ADICIONAR DISTAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face distal do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupDistal(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_DISTAL)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {

                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_DISTAL)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 18)
                            || (posicaodente >= 51 && posicaodente <= 55)) {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_DISTAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/distal.png");
                                graphicImage.setTitle("Face Distal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idDistalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face distal do dente " + nrDente + "!");
                    } else if ((posicaodente >= 41 && posicaodente <= 48)
                            || (posicaodente >= 81 && posicaodente <= 85)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_DISTAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/distal.png");
                                graphicImage.setTitle("Face Distal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idDistalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face distal do dente " + nrDente + "!");
                    } else if ((posicaodente >= 21 && posicaodente <= 28)
                            || (posicaodente >= 61 && posicaodente <= 65)) {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_DISTAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/mesial.png");
                                graphicImage.setTitle("Face Distal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idDistalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face distal do dente " + nrDente + "!");
                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_DISTAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/mesial.png");
                                graphicImage.setTitle("Face Distal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idDistalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face distal do dente " + nrDente + "!");
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }
            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER DISTAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;

    }

    /**
     * metodo para adicionar procedimento na Oclusal.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarOclusal(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_OCLUSAL, tbservico)) {

                String nrDente = ((UIComponent) event.getComponent().getChildren()
                        .get(0)).getAttributes().get("value").toString();

                final Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {

                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_OCLUSAL, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);
                        GraphicImage imagem = new GraphicImage();
                        imagem.setUrl("/resources/images/orcamento/oclusal_proc.png");
                        imagem.setTitle("Face Oclusal");
                        imagem.setStyle("border: 0px;");
                        imagem.setId("_idOclusalReplace" + nrDente);

                        OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_OCLUSAL, "adicionarPopupOclusal", "replacePopupOclusal", "removePopupOclusal", imagem.getId());
                        overlayPanel.setFor(imagem.getId());

                        event.getComponent().getChildren().set(1, imagem);
                        event.getComponent().getParent().getChildren().add(overlayPanel);
                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face oclusal do dente " + nrDente + "!");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO ADICIONAR OCLUSAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face ocludal do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupOclusal(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {

                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_OCLUSAL)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 28)
                            || (posicaodente >= 51 && posicaodente <= 65)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(8).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(8).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(8).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/oclusal.png");
                                graphicImage.setTitle("Face Oclusal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idOclusalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face oclusal do dente " + nrDente + "!");

                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(4).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(4).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_OCLUSAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(4).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/oclusal.png");
                                graphicImage.setTitle("Face Oclusal");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idOclusalInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face oclusal do dente " + nrDente + "!");
                    }

                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER OCLUSAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento!");
        }

        return result;
    }

    /**
     * metodo para adicionar procedimento na Mesial.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarMesial(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_MESIAL, tbservico)) {
                String nrDente = ((UIComponent) event.getComponent().getChildren()
                        .get(0)).getAttributes().get("value").toString();

                final Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {

                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_MESIAL, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);
                        Integer posicaodente = Integer.parseInt(nrDente);

                        if ((posicaodente >= 11 && posicaodente <= 18)
                                || (posicaodente >= 41 && posicaodente <= 48)
                                || (posicaodente >= 51 && posicaodente <= 55)
                                || (posicaodente >= 81 && posicaodente <= 85)) {

                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/mesial_proc.png");
                            imagem.setTitle("Face Mesial");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idMesialReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face mesial do dente " + nrDente + "!");
                        } else {

                            GraphicImage imagem = new GraphicImage();
                            imagem.setUrl("/resources/images/orcamento/distal_proc.png");
                            imagem.setTitle("Face Mesial");
                            imagem.setStyle("border: 0px;");
                            imagem.setId("_idMesialReplace" + nrDente);

                            OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_MESIAL, "adicionarPopupMesial", "replacePopupMesial", "removePopupMesial", imagem.getId());
                            overlayPanel.setFor(imagem.getId());

                            event.getComponent().getChildren().set(1, imagem);
                            event.getComponent().getParent().getChildren().add(overlayPanel);
                            event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                            result = true;
                            AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face mesial do dente " + nrDente + "!");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(" ERRO ADICIONAR MESIAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face mesial do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupMesial(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {
            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_MESIAL)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {
                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_MESIAL)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 18)
                            || (posicaodente >= 51 && posicaodente <= 55)) {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_MESIAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/mesial.png");
                                graphicImage.setTitle("Face Mesial");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idMesialInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso na face mesial do dente " + nrDente + "!");
                    } else if ((posicaodente >= 41 && posicaodente <= 48)
                            || (posicaodente >= 81 && posicaodente <= 85)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(9).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_MESIAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(5).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/mesial.png");
                                graphicImage.setTitle("Face Mesial");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idMesialInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso na face mesial do dente " + nrDente + "!");
                    } else if ((posicaodente >= 21 && posicaodente <= 28)
                            || (posicaodente >= 61 && posicaodente <= 65)) {

                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_MESIAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/distal.png");
                                graphicImage.setTitle("Face Mesial");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idMesialInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso na fase mesial do dente " + nrDente + "!");
                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_MESIAL);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(3).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/distal.png");
                                graphicImage.setTitle("Face Mesial");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idMesialInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso na fase mesial do dente " + nrDente + "!");
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER MESIAL: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;

    }

    /**
     * metodo para adicionar procedimento na palatina.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarPalatina(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico,
            final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {
            if (validarDadosAdicionarProcedimentoOdontograma(StatusSiso.IN_FACE_DENTE_PALATINA, tbservico)) {

                String nrDente = ((UIComponent) event.getComponent().getChildren()
                        .get(0)).getAttributes().get("value").toString();

                final Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

                if (tbdente != null) {

                    final Tbprocedimento p = procedimentoLogic.adicionarProcedimentoOdontograma(tbdente, StatusSiso.IN_FACE_DENTE_PALATINA, tbagendamento, tbservico);

                    if (p != null) {
                        listTbprocedimentos.add(p);

                        GraphicImage imagem = new GraphicImage();
                        imagem.setUrl("/resources/images/orcamento/palatina_proc.png");
                        imagem.setTitle("Face Palatina");
                        imagem.setStyle("border: 0px;");
                        imagem.setId("_idPalatinaReplace" + nrDente);

                        OverlayPanel overlayPanel = montarOverlayPanel(nrDente, StatusSiso.IN_FACE_DENTE_PALATINA, "adicionarPopupPalatina", "replacePopupPalatina", "removePopupPalatina", imagem.getId());
                        overlayPanel.setFor(imagem.getId());

                        event.getComponent().getChildren().set(1, imagem);
                        event.getComponent().getParent().getChildren().add(overlayPanel);
                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso na face palatina do dente " + nrDente + "!");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(" ERRO ADICIONAR VESTIBULAR: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
        }

        return result;
    }

    /**
     * metodo utilizado para remover procedimento da face palatina do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupPalatina(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren().get(0)).getAttributes().get("value").toString();

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                boolean achouProcedimento = false;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == StatusSiso.IN_FACE_DENTE_PALATINA)) {
                        achouProcedimento = true;
                        break;
                    }
                }

                if (achouProcedimento) {

                    List<Tbprocedimento> procedimentosRemove = new ArrayList<>();

                    for (Tbprocedimento procedimento : listTbprocedimentos) {

                        if ((tbdente.getIddente().intValue() != (procedimento.getIddente().getIddente()))
                                || (procedimento.getInfacedente() != StatusSiso.IN_FACE_DENTE_PALATINA)) {
                            procedimentosRemove.add(procedimento);
                        }
                    }

                    final Integer posicaodente = Integer.parseInt(nrDente);

                    if ((posicaodente >= 11 && posicaodente <= 28)
                            || (posicaodente >= 51 && posicaodente <= 65)) {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_PALATINA);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(11).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/palatina.png");
                                graphicImage.setTitle("Face Palatina");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idPalatinaInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face palatina do dente " + nrDente + "!");
                    } else {
                        GraphicImage graphicImage = (GraphicImage) event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getChildren().get(1);
                        UIComponent overlayPanel = event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().findComponent("overlayPanel" + graphicImage.getId() + StatusSiso.IN_FACE_DENTE_PALATINA);

                        if (overlayPanel != null) {
                            if (event.getComponent().getParent().getParent().getParent().getParent().getChildren().get(7).getParent().getChildren().remove(overlayPanel)) {
                                graphicImage.setUrl("/resources/images/orcamento/palatina.png");
                                graphicImage.setTitle("Face Palatina");
                                graphicImage.setStyle("border: 0px;");
                                graphicImage.setId("_idPalatinaInicial" + nrDente);
                            }
                        }

                        event.getComponent().processUpdates(FacesContext.getCurrentInstance());

                        result = true;
                        listTbprocedimentos.clear();
                        listTbprocedimentos.addAll(procedimentosRemove);
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso da face palatina do dente " + nrDente + "!");
                    }

                } else {
                    AbstractFacesContextUtils.addMessageWarn("Dente não possui procedimento para remoção.");
                }

            }

        } catch (Exception e) {
            System.out.println(" ERRO REMOVER PALATINA: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento!");
        }

        return result;

    }

    /**
     * metodo utilizado para verificar se pode adicionar procedimento a face
     * selecionada.
     *
     * @param inFace
     * @param tbservico
     * @return true or false
     */
    public boolean validarDadosAdicionarProcedimentoOdontograma(Integer inFace, final Tbservico tbservico) {

        boolean dadosValidos = true;

        Tbservicoface tbservicoface = tbservicofaceFacade.findTbservicofaceByTbservico(tbservico, super.getEM());

        if (tbservicoface != null) {
            switch (inFace) {
                case 1: // Cervical
                    if (!tbservicoface.getBocervical()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 3://distal
                    if (!tbservicoface.getBodistal()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 7://mesial
                    if (!tbservicoface.getBomesial()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 8://oclusal
                    if (!tbservicoface.getBooclusal()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 9://palatina
                    if (!tbservicoface.getBopalatina()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 10://raiz
                    if (!tbservicoface.getBoraiz()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 14://vestibular
                    if (!tbservicoface.getBovestibular()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                default:
                    break;
            }
        } else {
            dadosValidos = false;
            AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face pois não possui configuração!");
        }

        return dadosValidos;

    }

    /**
     * metodo utilizado para montar o popup para remover um procedimento do
     * dente.
     *
     * @param nrDente
     * @param inFace
     * @param nmMetodoAdd
     * @param nmMetodoReplace
     * @param nmMetodoRemove
     * @return OverlayPanel
     */
    private OverlayPanel montarOverlayPanel(String nrDente, Integer inFace, String nmMetodoAdd, String nmMetodoReplace, String nmMetodoRemove, String idGraphicImage) {
        Class[] actionevent = new Class[]{ActionEvent.class};

        OverlayPanel overlayPanel = new OverlayPanel();
        overlayPanel.setId("overlayPanel" + idGraphicImage + inFace);
        overlayPanel.setStyleClass("cssLinhaClara");
        overlayPanel.setStyle("border:1px;");
        overlayPanel.setShowCloseIcon(true);
        overlayPanel.setShowEvent("mouseover");
        overlayPanel.setShowEffect("blind");
        overlayPanel.setHideEffect("blind");
        overlayPanel.setDismissable(true);

        HtmlPanelGrid panelGridPopup = new HtmlPanelGrid();
        panelGridPopup.setCellpadding("2");
        panelGridPopup.setCellspacing("2");
        panelGridPopup.setStyleClass("cssLinhaClara");

        HtmlPanelGrid panelGridInfo = new HtmlPanelGrid();
        panelGridInfo.setColumns(4);

        OutputLabel dente = new OutputLabel();
        dente.setValue("Dente : ");
        dente.setStyleClass("text-bold");
        dente.setId("_idOTDente" + idGraphicImage + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(dente);

        OutputLabel nrdente = new OutputLabel();
        nrdente.setValue(nrDente);
        nrdente.setStyleClass("cssLabelAtributo");
        nrdente.setId("_idOTnrdente" + idGraphicImage + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(nrdente);

        OutputLabel face = new OutputLabel();
        face.setValue("Face : ");
        face.setStyleClass("text-bold");
        face.setId("_idOTFace" + idGraphicImage + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(face);

        OutputLabel nrface = new OutputLabel();
        nrface.setValue(mostrarFaceDente(inFace));
        nrface.setStyleClass("cssLabelAtributo");
        nrface.setId("_idOTnrface" + idGraphicImage + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(nrface);

        HtmlPanelGrid panelGridTitulo = new HtmlPanelGrid();

        OutputLabel titulo = new OutputLabel();
        titulo.setValue("Selecione a opção desejada".toUpperCase());
        titulo.setStyleClass("text-bold");
        titulo.setId("_idTitulo" + idGraphicImage + nrDente + inFace.toString());

        panelGridTitulo.getChildren().add(titulo);

        HtmlPanelGrid panelGrid = new HtmlPanelGrid();
        panelGrid.setColumns(1);

        // Botão Realizar
        HtmlCommandButton linkPopupAdd = new HtmlCommandButton();
        linkPopupAdd.setValue("Adicionar");
        linkPopupAdd.setStyleClass("cssbtn");
        linkPopupAdd.setOnclick("PF('statusDialog').show()");
        linkPopupAdd.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                "#{atendimentoBean." + nmMetodoAdd + "}", actionevent));

        UIParameter paramDente1 = new UIParameter();
        paramDente1.setName("idDente");
        paramDente1.setValue(nrDente);

        linkPopupAdd.getChildren().add(paramDente1);

        UIParameter paramFace1 = new UIParameter();
        paramFace1.setName("idFace");
        paramFace1.setValue(inFace);

        linkPopupAdd.getChildren().add(paramFace1);

//        panelGrid.getChildren().add(linkPopupAdd);
        // Botão não Realizar
        HtmlCommandButton linkPopupReplace = new HtmlCommandButton();
        linkPopupReplace.setValue("Remover");
        linkPopupReplace.setOnclick("PF('statusDialog').show()");
        linkPopupReplace.setStyleClass("cssbtn");
        linkPopupReplace.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                "#{atendimentoBean." + nmMetodoRemove + "}", actionevent));

        UIParameter paramDente2 = new UIParameter();
        paramDente2.setName("idDente");
        paramDente2.setValue(nrDente);
        linkPopupReplace.getChildren().add(paramDente2);

        UIParameter paramFace2 = new UIParameter();
        paramFace2.setName("idFace");
        paramFace2.setValue(inFace);
        linkPopupReplace.getChildren().add(paramFace2);

        panelGrid.getChildren().add(linkPopupReplace);

        panelGridPopup.getChildren().add(panelGridTitulo);
        panelGridPopup.getChildren().add(panelGridInfo);
        panelGridPopup.getChildren().add(panelGrid);

        overlayPanel.getChildren().add(panelGridPopup);

        return overlayPanel;

    }

    /**
     * metodo utitlizado para montar popup atendimento do paciente.
     *
     * @param nrDente
     * @param inFace
     * @return OverlayPanel
     */
    private OverlayPanel montarOverlayPanelAtendimento(String nrDente, Integer inFace, String idGraphicImage) {
        Class[] actionevent = new Class[]{ActionEvent.class};

        OverlayPanel overlayPanel = new OverlayPanel();
        overlayPanel.setId("overlayPanelAtendimento" + idGraphicImage + inFace);
        overlayPanel.setStyleClass("cssLinhaClara");
        overlayPanel.setStyle("border:1px;");
        overlayPanel.setShowEvent("mouseover");
        overlayPanel.setShowCloseIcon(true);
        overlayPanel.setShowEffect("blind");
        overlayPanel.setHideEffect("blind");
        overlayPanel.setDismissable(true);

        HtmlPanelGrid panelGridPopup = new HtmlPanelGrid();
        panelGridPopup.setCellpadding("2");
        panelGridPopup.setCellspacing("2");
        panelGridPopup.setStyleClass("cssLinhaClara");

        HtmlPanelGrid panelGridInfo = new HtmlPanelGrid();
        panelGridInfo.setColumns(4);

        HtmlOutputText dente = new HtmlOutputText();
        dente.setValue("Dente : ");
        dente.setStyleClass("text-bold");
        dente.setId("_idOTDente" + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(dente);

        HtmlOutputText nrdente = new HtmlOutputText();
        nrdente.setValue(nrDente);
        nrdente.setStyleClass("cssLabelAtributo");
        nrdente.setId("_idOTnrdente" + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(nrdente);

        HtmlOutputText face = new HtmlOutputText();
        face.setValue("Face : ");
        face.setStyleClass("text-bold");
        face.setId("_idOTFace" + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(face);

        HtmlOutputText nrface = new HtmlOutputText();
        nrface.setValue(mostrarFaceDente(inFace));
        nrface.setStyleClass("cssLabelAtributo");
        nrface.setId("_idOTnrface" + nrDente + inFace.toString());
        panelGridInfo.getChildren().add(nrface);

        HtmlPanelGrid panelGridTitulo = new HtmlPanelGrid();

        HtmlOutputText titulo = new HtmlOutputText();
        titulo.setValue("Selecione a opção desejada".toUpperCase());
        titulo.setStyleClass("text-bold");
        titulo.setId("_idTitulo" + nrDente + inFace.toString());

        panelGridTitulo.getChildren().add(titulo);

        HtmlPanelGrid panelGrid = new HtmlPanelGrid();
        panelGrid.setColumns(2);

        // Botão Realizar
        HtmlCommandButton linkPopupAdd = new HtmlCommandButton();
        linkPopupAdd.setValue("Iniciar");
        linkPopupAdd.setStyleClass("cssbtn");
        linkPopupAdd.setId("_idButtonRealizar" + nrDente + inFace.toString());
        linkPopupAdd.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                "#{atendimentoBean.iniciarProcedimentoOdontograma}", actionevent));

        UIParameter paramDente1 = new UIParameter();
        paramDente1.setName("idDente");
        paramDente1.setValue(nrDente);

        linkPopupAdd.getChildren().add(paramDente1);

        UIParameter paramFace1 = new UIParameter();
        paramFace1.setName("idFace");
        paramFace1.setValue(inFace);

        linkPopupAdd.getChildren().add(paramFace1);

        panelGrid.getChildren().add(linkPopupAdd);

        // Botão não Realizar
        HtmlCommandButton linkPopupReplace = new HtmlCommandButton();
        linkPopupReplace.setValue("Finalizar");
        linkPopupReplace.setStyleClass("cssbtn");
        linkPopupReplace.setId("_idButtonNaoRealizar" + nrDente + inFace.toString());
        linkPopupReplace.setActionListener(FacesContext.getCurrentInstance().getApplication().createMethodBinding(
                "#{atendimentoBean.finalizarProcedimentoOdontograma}", actionevent));

        UIParameter paramDente2 = new UIParameter();
        paramDente2.setName("idDente");
        paramDente2.setValue(nrDente);
        linkPopupReplace.getChildren().add(paramDente2);

        UIParameter paramFace2 = new UIParameter();
        paramFace2.setName("idFace");
        paramFace2.setValue(inFace);
        linkPopupReplace.getChildren().add(paramFace2);

        panelGrid.getChildren().add(linkPopupReplace);

        panelGridPopup.getChildren().add(panelGridTitulo);
        panelGridPopup.getChildren().add(panelGridInfo);
        panelGridPopup.getChildren().add(panelGrid);

        overlayPanel.getChildren().add(panelGridPopup);

        return overlayPanel;
    }

    private String mostrarFaceDente(Integer inFace) {
        String face;

        switch (inFace) {
            case 1:
                face = "Cervical";
                break;

            case 3:
                face = "Distal";
                break;

            case 7:
                face = "Mesial";
                break;

            case 8:
                face = "Oclusal";
                break;

            case 9:
                face = "Palatina";
                break;

            case 10:
                face = "Raiz";
                break;

            case 14:
                face = "Vestibular";
                break;

            default:
                face = "";
                break;
        }

        return face;
    }

    /**
     * metodo utilizado para iniciar procedimento pelo odontograma.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean iniciarProcedimentoOdontograma(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {

            String nrDente = ((UIComponent) event.getComponent().getChildren()
                    .get(0)).getAttributes().get("value").toString();

            Integer nrFace = Integer.parseInt(((UIComponent) event.getComponent().getChildren()
                    .get(1)).getAttributes().get("value").toString());

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                Tbprocedimento tbprocedimento = null;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == nrFace)) {
                        tbprocedimento = procedimento;
                        break;
                    }
                }

                if (tbprocedimento != null) {
                    if (tbprocedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPOSTATUS_PROCEDIMENTO_NAO_REALIZADOS)) {
                        tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_EM_TRATAMENTO));
                        tbprocedimento.setDtinclusaolog(new Date());
                        tbprocedimento.setDtatualizacaolog(new Date());

                        if (procedimentoLogic.editTbprocedimento(tbprocedimento)) {
                            result = true;
                        }

                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado para iniciar!");
                }

            } else {
                AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
            }

        } catch (Exception e) {
            System.out.println("ERRO INICIAR PROCEDIMENTO ODONTOGRAMA: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Não é possível iniciar procedimento!");
        }

        return result;
    }

    /**
     * metodo utilizado para finalizar procedimento odontograma.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean finalizarProcedimentoOdontograma(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;

        try {
            String nrDente = ((UIComponent) event.getComponent().getChildren()
                    .get(0)).getAttributes().get("value").toString();

            Integer nrFace = Integer.parseInt(((UIComponent) event.getComponent().getChildren()
                    .get(1)).getAttributes().get("value").toString());

            Tbdente tbdente = denteLogic.findTbdenteByNrPosicao(Integer.parseInt(nrDente));

            if (tbdente != null) {

                Tbprocedimento tbprocedimento = null;

                for (Tbprocedimento procedimento : listTbprocedimentos) {

                    if ((tbdente.getIddente().intValue() == (procedimento.getIddente().getIddente()))
                            && (procedimento.getInfacedente() == nrFace)) {
                        tbprocedimento = procedimento;
                        break;
                    }
                }

                if (tbprocedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPO_STATUS_PROCEDIMENTO_EM_TRATAMENTO)) {
                    tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_REALIZADOS));
                    tbprocedimento.setDtconclusao(new Date());
                    tbprocedimento.setDtatualizacaolog(new Date());

                    if (procedimentoLogic.editTbprocedimento(tbprocedimento)) {
                        result = true;
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado para finalizar!");
                }
            } else {
                AbstractFacesContextUtils.addMessageWarn("Dente não cadastrado no sistema!");
            }

        } catch (Exception e) {
            System.out.println("ERRO FINALIZAR PROCEDIMENTO ODONTOGRAMA: " + e.getMessage());
            AbstractFacesContextUtils.addMessageWarn("Não é possível finalizar procedimento!");
        }

        return result;
    }
}
