/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.converter;

import br.com.siso.web.faces.constants.Resources;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author ioliveira
 */
public final class TimeControl {

    private static final String STRZEROS = ":00";

    private TimeControl() {
    }

    /**
     * Metodo para exibir a data<br> No padr\u00E3o "dd"
     *
     * @return Retorna uma String com a data
     */
    public static String pegaODia() {
        DateFormat dateFormat = new SimpleDateFormat("dd");

        return dateFormat.format(new Date());
    }

    /**
     * Metodo para exibir a data<br> No padr�o "ddMMyyyy"
     *
     * @return Retorna uma String com a data
     */
    public static String pegaDia() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * metodo para trazer os dois digitos do ano No padr\u00E3o yyyy
     *
     * @return retorna um inteiro
     */
    public static String pegaOAno() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(new Date());
    }

    /**
     * Metodo para exibir a data<br> No padr\u00E3o "MM"
     *
     * @return Retorna uma String com a data
     */
    public static String pegaMes() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        return dateFormat.format(new Date());
    }

    /**
     * Metodo para exibir a data<br> No padr�o "dd/MM/yyyy"
     *
     * @return Retorna uma String com a data
     */
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    /**
     * Metodo para trazer data anterior ao dia vigente No padr\u00E3o
     * "yyyy-mm-dd"
     *
     * @return Retorna uma string
     */
    public static String pegaDiaAnterior() {
        GregorianCalendar calendario = new GregorianCalendar();
        int intDia = calendario.get(GregorianCalendar.DAY_OF_MONTH);
        int intMes = calendario.get(GregorianCalendar.MONTH);
        int intAno = calendario.get(GregorianCalendar.YEAR);

        calendario.set(intAno, intMes, intDia - 1);
        intDia = calendario.get(GregorianCalendar.DAY_OF_MONTH);
        intMes = calendario.get(GregorianCalendar.MONTH);
        intAno = calendario.get(GregorianCalendar.YEAR);

        return intAno + "-" + (intMes + 1) + "-" + (intDia - 1);
    }

    /**
     * Metodo para converte data para o juliano No padr\u00E3o "yddd"
     *
     * @param ano
     * @param intMes
     * @param dia
     * @return retorna uma string
     */
    public static String converteDiaJuliano(int ano, int intMes, int dia) {
        StringBuffer strDiaJ = new StringBuffer();
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(ano, intMes - 1, dia);
        strDiaJ.append(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)));
        while (strDiaJ.length() < 3) {
            strDiaJ.insert(0, '0');
        }
        return String.valueOf(ano).substring(3, 4) + strDiaJ;
    }

    /**
     * Metodo para trazer data juliano No padr\u00E3o "yddd"
     *
     * @return retorna uma string
     */
    public static String pegaDiaJuliano() {
        StringBuffer strDiaJ = new StringBuffer();
        Calendar cal = GregorianCalendar.getInstance();

        strDiaJ.append(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)));

        while (strDiaJ.length() < 3) {
            strDiaJ.insert(0, '0');
        }

        return pegaFimAno().charAt(1) + strDiaJ.toString();
    }

    /**
     * Metodo para exibir a data<br> No padr\u00E3o "yyyyMMdd"
     *
     * @return Retorna uma String com a data
     */
    public static String pegaDiaPInternacional() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    /**
     * Metodo para exibir a data<br> No padr\u00E3o "ddMMyy"
     *
     * @return Retorna uma String com a data
     */
    public static String pegaPLote() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        return dateFormat.format(new Date());
    }

    /**
     * Metodo para trazer a data posterior ao dia vigente No padr\u00E3o
     * "yyyy-mm-dd"
     *
     * @return Retorna uma string
     */
    public static String pegaDiaSeguinte() {
        GregorianCalendar calendario = new GregorianCalendar();
        int intDia = calendario.get(GregorianCalendar.DAY_OF_MONTH);
        int intMes = calendario.get(GregorianCalendar.MONTH);
        int intAno = calendario.get(GregorianCalendar.YEAR);
        calendario.set(intAno, intMes, intDia - 1);
        intDia = calendario.get(GregorianCalendar.DAY_OF_MONTH);
        intMes = calendario.get(GregorianCalendar.MONTH);
        intAno = calendario.get(GregorianCalendar.YEAR);
        return intAno + "-" + (intMes + 1) + "-" + (intDia + 1);
    }

    /**
     * metodo para trazer os dois digitos do ano No padr\u00E3o "y"
     *
     * @return retorna um inteiro
     */
    public static String pegaFimAno() {
        DateFormat dateFormat = new SimpleDateFormat("YY");
        return dateFormat.format(new Date());
    }

    public static String formatSaidaPadrao(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");

        return dateFormat.format(date);
    }

    /**
     * Metodo para retorna a data e hora anterior cheia para o padrao informix
     * No padr\u00E3o "yyyy-mm-dd HH:00:00"
     *
     * @return retorna uma string
     */
    public static String retornaDaHoCheioAntInformix() {
        String strResult;

        if (Integer.parseInt(retornaHoraAtual()) == 00) {
            strResult = pegaDiaAnterior() + " " + 23 + STRZEROS + STRZEROS;
        } else {
            strResult = retornaDataInformix() + " " + (Integer.parseInt(retornaHoraAtual()) - 1) + STRZEROS + STRZEROS;
        }

        return strResult;
    }

    /**
     * Metodo para retorna a data e hora cheia para o padrao informix No
     * padr\u00E3o "yyyy-mm-dd HH:00:00"
     *
     * @return retorna uma string
     */
    public static String retornaDaHoCheioAtualInformix() {
        return (retornaDataInformix() + " " + retornaHoraAtual() + STRZEROS + STRZEROS);
    }

    /**
     * Metodo para retorna a data e proxima hora cheia para o padrao informix No
     * padr\u00E3o "yyyy-mm-dd HH:00:00"
     *
     * @return retorna uma string
     */
    public static String retornaDaHoCheioProxInformix() {
        String strResult;
        int intHoraProx = Integer.parseInt(retornaHoraAtual()) + 1;

        if (intHoraProx == 24) {
            strResult = (pegaDiaSeguinte() + " " + STRZEROS + STRZEROS + STRZEROS);
        } else {
            strResult = retornaDataInformix() + " " + (Integer.parseInt(retornaHoraAtual()) + 1) + STRZEROS + STRZEROS;
        }

        return strResult;

    }

    public static String pegaHora() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

        return dateFormat.format(new Date());
    }

    public static String pegaHoraHH() {
        DateFormat df = new SimpleDateFormat("HH");

        return df.format(new Date());
    }

    /**
     * Metodo para trazer data e hora no padrao Informix No padr\u00E3o
     * "yyyy-MM-dd hh:mm:ss
     *
     * @return retorna uma string
     */
    public static String retornaDaHoInformix() {
        return (retornaDataInformix() + " " + pegaHora());
    }

    /**
     * Metodo para trazer data no padrao Informix No padr\u00E3o "yyyy-mm-dd"
     *
     * @return Retorna uma String
     */
    public static String retornaDataInformix() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(new Date());
    }

    /**
     * Metodo para retorna a hora atual No padr\u00E3o "HH"
     *
     * @return retorna uma string
     */
    public static String retornaHoraAtual() {
        GregorianCalendar calendario = new GregorianCalendar();
        int intHor = calendario.get(GregorianCalendar.HOUR_OF_DAY);
        String hora = ((intHor < 10)
                ? "0"
                : "") + intHor;

        return hora;
    }

    public static String retornaHoraAtualPadrao() {
        GregorianCalendar calendario = new GregorianCalendar();
        int intHor = calendario.get(GregorianCalendar.HOUR_OF_DAY);
        String hora = ((intHor < 10)
                ? "0"
                : "") + intHor;

        return hora + ":00:00";
    }

    public static String retornaHoraAnterior() {
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.add(Calendar.HOUR_OF_DAY, -1);
        int intHor = calendario.get(GregorianCalendar.HOUR_OF_DAY);

        String hora = ((intHor < 10)
                ? "0"
                : "") + intHor;
        return hora;
    }

    public static String retornaHoraAnteriorPadrao() {
        GregorianCalendar calendario = new GregorianCalendar();
        calendario.add(Calendar.HOUR_OF_DAY, -1);
        int intHor = calendario.get(GregorianCalendar.HOUR_OF_DAY);

        String hora = ((intHor < 10)
                ? "0"
                : "") + intHor;

        return hora + ":00:00";
    }

    /**
     * Metodo para retorna o minuto atual No padr\u00E3o "mm"
     *
     * @return retorna uma string
     */
    public static String retornaMinutoAtual() {
        GregorianCalendar calendario = new GregorianCalendar();
        int intMinuto = calendario.get(GregorianCalendar.MINUTE);
        String minuto = ((intMinuto < 10)
                ? "0"
                : "") + intMinuto;

        return minuto;
    }

    /**
     * Metodo para retorna a semana do ano No padr\u00E3o "SSS"
     *
     * @return
     */
    public static String retornaSemanaDoAno() {
        String strDiaJ;
        Calendar cal = GregorianCalendar.getInstance();

        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        if ((cal.get(GregorianCalendar.MONTH) + 1) == 12 && (cal.get(Calendar.WEEK_OF_YEAR) == 1)) {
            strDiaJ = String.valueOf(cal.getActualMaximum(Calendar.WEEK_OF_YEAR) + 1);
        } else {
            strDiaJ = String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
        }

        return strDiaJ;
    }

    public static String getTimeIso() {
        return ConvData.parseToStringIsoSimple(Calendar.getInstance().getTime());
    }

    /**
     *
     * @return yyyy-MM-dd HH:mm:ss.ms
     */
    public static String getDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * Date now with hour, minutes,second is zero
     *
     * @return
     */
    public static Date getDateIni() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * Retorna o primeiro dia do Ano corrente.
     *
     * @return 01-01-YYYY 00:00:00-00
     */
    public static Date getFirstDayOfYear() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        return calendar.getTime();
    }

    public static Date getDateFim() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);
        return c.getTime();
    }

    public static List<String> getListMonth() {

        List<String> months = new ArrayList<>();
        months.add(Resources.getField("janeiro"));
        months.add(Resources.getField("fevereiro"));
        months.add(Resources.getField("marco"));
        months.add(Resources.getField("abril"));
        months.add(Resources.getField("maio"));
        months.add(Resources.getField("junho"));
        months.add(Resources.getField("julho"));
        months.add(Resources.getField("agosto"));
        months.add(Resources.getField("setembro"));
        months.add(Resources.getField("outubro"));
        months.add(Resources.getField("novembro"));
        months.add(Resources.getField("dezembro"));

        return months;
    }

    public static String getMonthInt(String month) {

        String mesSelected = "01";

        switch (month) {
            case "Janeiro":
                mesSelected = "01";
                break;
            case "January":
                mesSelected = "01";
                break;
            case "Fevereiro":
                mesSelected = "02";
                break;
            case "February":
                mesSelected = "02";
                break;
            case "Março":
                mesSelected = "03";
                break;
            case "March":
                mesSelected = "03";
                break;
            case "Abril":
                mesSelected = "04";
                break;
            case "April":
                mesSelected = "04";
                break;
            case "Maio":
                mesSelected = "05";
                break;
            case "May":
                mesSelected = "05";
                break;
            case "Junho":
                mesSelected = "06";
                break;
            case "June":
                mesSelected = "06";
                break;
            case "Julho":
                mesSelected = "07";
                break;
            case "July":
                mesSelected = "07";
                break;
            case "Agosto":
                mesSelected = "08";
                break;
            case "August":
                mesSelected = "08";
                break;
            case "Setembro":
                mesSelected = "09";
                break;
            case "September":
                mesSelected = "09";
                break;
            case "Outubro":
                mesSelected = "10";
                break;
            case "October":
                mesSelected = "10";
                break;
            case "Novembro":
                mesSelected = "11";
                break;
            case "November":
                mesSelected = "11";
                break;
            case "Dezembro":
                mesSelected = "12";
                break;
            case "December":
                mesSelected = "12";
                break;
        }

        return mesSelected;
    }

    public static Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * metodo que carrega todas as horas.
     *
     * @return list of selectItem
     */
    public static List<SelectItem> loadListOfAllHours() {
        List<SelectItem> listOfHours = new ArrayList<>();

        listOfHours.add(new SelectItem(00, "00"));
        listOfHours.add(new SelectItem(01, "01"));
        listOfHours.add(new SelectItem(02, "02"));
        listOfHours.add(new SelectItem(03, "03"));
        listOfHours.add(new SelectItem(04, "04"));
        listOfHours.add(new SelectItem(05, "05"));
        listOfHours.add(new SelectItem(06, "06"));
        listOfHours.add(new SelectItem(07, "07"));
        listOfHours.add(new SelectItem(08d, "08"));
        listOfHours.add(new SelectItem(09d, "09"));
        listOfHours.add(new SelectItem(10, "10"));
        listOfHours.add(new SelectItem(11, "11"));
        listOfHours.add(new SelectItem(12, "12"));
        listOfHours.add(new SelectItem(13, "13"));
        listOfHours.add(new SelectItem(14, "14"));
        listOfHours.add(new SelectItem(15, "15"));
        listOfHours.add(new SelectItem(16, "16"));
        listOfHours.add(new SelectItem(17, "17"));
        listOfHours.add(new SelectItem(18, "18"));
        listOfHours.add(new SelectItem(19, "19"));
        listOfHours.add(new SelectItem(20, "20"));
        listOfHours.add(new SelectItem(21, "21"));
        listOfHours.add(new SelectItem(22, "22"));
        listOfHours.add(new SelectItem(23, "23"));

        return listOfHours;
    }

    /**
     * metodo que carrega todos os minutos.
     *
     * @return list of selectItem
     */
    public static List<SelectItem> loadListOfAllMinutes() {
        List<SelectItem> listOfHours = new ArrayList<>();

        for (int i = 0; i < 60; i++) {

            if (String.valueOf(i).length() == 1) {
                listOfHours.add(new SelectItem("0" + i, "0" + i));
            } else {
                listOfHours.add(new SelectItem(i, String.valueOf(i)));
            }
        }

        return listOfHours;
    }
}
