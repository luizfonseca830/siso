/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.utils;

import br.com.siso.web.faces.converter.ConvData;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ioliveira
 */
@ManagedBean
@RequestScoped
public class WebBeanUtils {

    /**
     *
     * @param xml
     * @return dd/MM/yyyy HH:mm:ss
     */
    public String xmlToStringBr(XMLGregorianCalendar xml) {
        return ConvData.convDateToStringDateBr(ConvData.convXMLToDate(xml));
    }

    /**
     *
     * @param date
     * @return dd/MM/yyyy HH:mm:ss
     */
    public String dateToStringBr(Date date) {
        return ConvData.convDateToStringDateBr(date);
    }

    /**
     *
     * @param date
     * @return dd/MM/yyy
     */
    public String formatDtBr(Date date) {
        return ConvData.formatDtBr(date);
    }

    /**
     *
     * @param date
     * @return HH:mm
     */
    public String formatHourMinutes(Date date) {
        String result;
        try {
            result = ConvData.formatHHmm(date);
        } catch (Exception e) {
            result = "-/-";
        }
        return result;
    }

    /**
     *
     * @param number
     * @return
     */
    public String formatNumberInt(int number) {
        DecimalFormat format = new DecimalFormat();
        return format.format(number);
    }

    public String formatNumberDouble(double number) {
        DecimalFormat format = new DecimalFormat();
        return format.format(number);
    }

    /**
     *
     * @param number
     * @param fraction
     * @return 00.000.000,0000
     */
    public String formatNumberCurrencyInstance(double number, int fraction) {
        String numberFormatRst = "";
        try {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            numberFormat.setMaximumFractionDigits(fraction);
            numberFormat.setMinimumFractionDigits(fraction);
            numberFormatRst = numberFormat.format(number).substring(2);
        } catch (Exception n) {

        }
        return numberFormatRst;
    }

    /**
     *
     * @param date
     * @return YYYY
     */
    public int getYear(Date date) {
        return ConvData.getAno(date);
    }

    /**
     *
     * @return Ip do Servidor
     */
    public String getServerIp() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getLocalAddr();
    }

    /**
     * Retorna a data de hoje
     *
     * @return
     */
    public Date getDate() {
        return new Date();
    }

    public String getDifferenceBetweenTwoHours(String dateInicial, String dateFinal) {
        return ConvData.getTimeDifferenceBetweenTwoDates(dateInicial, dateFinal);
    }

    /**
     *
     * @param date
     * @return dd
     */
    public int getDayFromDate(Date date) {
        return ConvData.getDayByDate(date);
    }

    /**
     *
     * @param date
     * @return MM
     */
    public int getMonthFromDate(Date date) {
        return ConvData.getMonthByDate(date);
    }

    /**
     * Converte um numero hexadecimal para um decimal.
     *
     * @param hexNumber
     * @return
     */
    public long converterHexToDecimal(String hexNumber) {
        long decimal = 0;
        try {
            decimal = Integer.parseInt(hexNumber, 16);
        } catch (NumberFormatException n) {

        }
        return decimal;
    }

}
