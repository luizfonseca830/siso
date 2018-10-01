/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ioliveira
 */
public final class ConvData {

    private static final String STRERRODEFAULT = "Parse Date Error";
    private static final SimpleDateFormat formatIsoData = new SimpleDateFormat("yy-MM-dd");
    private static final SimpleDateFormat formatIso = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatIso2 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatIsoNormal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatBra2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat formatBra1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static final SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat formatHH = new SimpleDateFormat("HH");
    private static final SimpleDateFormat formatDtBr = new SimpleDateFormat("dd/MM/yyy");
    private static final SimpleDateFormat formatHHmm = new SimpleDateFormat("HH:mm");
    private static final DateFormat formatForParser = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat formatLn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date date;

    private ConvData() {
    }

    /**
     *
     * @param strDate
     * @return
     */
    public static Date parseToDateIso(String strDate) {
        try {
            return formatIsoNormal.parse(strDate);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static String parseToStringIso(Date date) {
        return formatIsoNormal.format(date);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String parseToStringIsoSimple(Date date) {
        return formatIsoData.format(date);
    }

    /**
     * Converte uma data no formato ABNT em formato ISO;
     *
     * @param dataBra Argumento que recebe data no formato ABNT(dd/MM/yyyy) ou
     * (dd-mm-yyyy);
     * @return Uma data no formato ISO(20yy-MM-dd HH:mm:SS).
     */
    public static String parseDataIso(String dataBra) {
        try {
            if (dataBra.contains("/")) {
                date = formatBra2.parse(dataBra);
            } else {
                date = formatBra1.parse(dataBra);
            }

            return ("20" + formatIso.format(date));
        } catch (ParseException e) {
            return (STRERRODEFAULT);
        }
    }

    public static Date parseDataIsoToDate(String dataBra) {
        try {
            return formatIso.parse(parseDataIso(dataBra));
        } catch (ParseException ex) {
            Logger.getLogger(ConvData.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public static String parseDatatoIsoEn(Date date) {
        try {
            return formatIso2.format(date);
        } catch (Exception e) {
            return STRERRODEFAULT;
        }
    }

    /**
     * Converte uma data no formato ABNT em formato ISO;
     *
     * @param dataBra Argumento que recebe data no formato ABNT(dd/MM/yyyy) ou
     * (dd-mm-yyyy);
     * @return Uma data no formato ISO(20yy-MM-dd).
     */
    public static String parseDataIsoDate(String dataBra) {
        try {
            if (dataBra.contains("/")) {
                date = formatBra2.parse(dataBra);
            } else {
                date = formatBra1.parse(dataBra);
            }

            return ("20" + formatIsoData.format(date));
        } catch (ParseException e) {
            return (STRERRODEFAULT);
        }
    }

    /**
     * Converte uma data no formato ISO em formato ABNT;
     *
     * @param dataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
     * @return Uma data no formato ABNT(dd/MM/yyyy).
     */
    public static String parseDataBra1(String dataIso) {
        try {
            date = formatIso.parse(dataIso);

            return (formatBra1.format(date));
        } catch (ParseException e) {
            return (STRERRODEFAULT);
        }
    }

    /**
     * Converte uma data no formato ISO em formato ABNT;
     *
     * @param dataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
     * @return Uma data no formato ABNT(dd/MM/yyyy).
     */
    public static String parseDataBra2(String dataIso) {
        try {
            date = formatIso.parse(dataIso);

            return (formatBra2.format(date));
        } catch (ParseException e) {
            return (STRERRODEFAULT);
        }
    }

    public static Date convXMLToDate(XMLGregorianCalendar gregData) {
        Date data = null;

        if (gregData != null) {

            // data = new Date(gregData.getYear(), gregData.getMonth(), gregData.getDay(), gregData.getHour(), gregData.getMinute(), gregData.getSecond());
            data = gregData.toGregorianCalendar().getTime();
        }

        return data;
    }

    public static XMLGregorianCalendar convDateToXML(Date data) {
        GregorianCalendar gregDate;
        XMLGregorianCalendar xmlgc = null;
        DateFormat year = new SimpleDateFormat("yyyy");
        DateFormat month = new SimpleDateFormat("MM");
        DateFormat day = new SimpleDateFormat("dd");

        try {
            gregDate = new GregorianCalendar();
            gregDate.set(Integer.parseInt(year.format(data)), Integer.parseInt(month.format(data)), Integer.parseInt(day.format(data)));
            xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregDate);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ConvData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlgc;
    }

    public static Date convStringToDateIso(String strData) {
        Date dateResult;
        try {
            dateResult = formatIsoData.parse(strData);
        } catch (ParseException ex) {
            dateResult = new Date();
        }
        return dateResult;
    }

    /**
     *
     * @param dteData
     * @return dd/MM/yyyy HH:mm:ss
     */
    public static String convDateToStringDateBr(Date dteData) {
        String strResult = "";
        try {
            strResult = formatBra2.format(dteData);
        } catch (Exception e) {
        }
        return strResult;
    }

    public static String convDateToHHmmss(Date date) {
        String result;
        try {
            result = formatTime.format(date);
        } catch (Exception ex) {
            result = "";
        }
        return result;
    }

    /**
     *
     * @param date
     * @return HH
     */
    public static int convDateToHH(Date date) {
        int result;
        try {
            result = Integer.valueOf(formatHH.format(date));
        } catch (Exception ex) {
            result = 0;
        }
        return result;
    }

    public static int getDayByDate(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(date.getTime());
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonthByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        return c.get(Calendar.MONTH) + 1;
    }

    public static String getTimeDifference(Date datafim) {
        String result;
        // Quantidade de milissegundos em um dia
        int tempoDia = 1000 * 60 * 60 * 24;

        // Data Final
        Calendar dataFinal = Calendar.getInstance();

        // Atribui a datafinal
        dataFinal.setTime(datafim);

        // Data de hoje
        Calendar dataHoje = Calendar.getInstance();

        // Calcula a diferenï¿½a de DIAS entre hoje e da data de final
        long diferenca = dataHoje.getTimeInMillis() - dataFinal.getTimeInMillis();
        //long diasDiferenca = diferenca / tempoDia;

        //Calcula a quantidade de HORAS
        String horas = milisegundosParaHora(diferenca);

        result = horas;

        return result;
    }

    public static String milisegundosParaHora(long tempo) {
        String hora = "";
        long secs = tempo / 1000;
        if (tempo == 0) {
            hora = "0";
        } else {
            int[] ret = new int[4];
            // calcula nmero de horas, minutos e segundos
            ret[3] = (int) secs / 86400;
            secs = (int) secs % 86400;
            ret[0] = (int) secs / 3600;
            secs = secs % 3600;
            ret[1] = (int) secs / 60;
            secs = (int) secs % 60;
            ret[2] = (int) secs;
            if (ret[3] != 0) {
                hora += ret[3] + " dias, ";
            }
            if (ret[0] != 0) {
                hora += ret[0] + " horas, ";
            }
            if (ret[1] != 0) {
                hora += ret[1] + " min";
            }
        }       // fim do if (tempo == 0)

        return hora;
    }

//    public static String getTimeDifference(Date date) {
//        String result = "";
//        int dia, hora, minutos;
//        Calendar dataAtual = GregorianCalendar.getInstance();
//        Calendar dataStatus = new GregorianCalendar();
//        dataStatus.setTime(date);
//
//        dia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataStatus.get(Calendar.DAY_OF_MONTH);
//        hora = dataAtual.get(Calendar.HOUR_OF_DAY) - dataStatus.get(Calendar.HOUR_OF_DAY);
//        minutos = dataAtual.get(Calendar.MINUTE) - dataStatus.get(Calendar.MINUTE);
//
//        if (dia > 0) {
//            if (dia == 1) {
//                result += dia + " dia";
//            } else {
//                result += dia + " dias";
//            }
//        }
//        if (hora > 0) {
//            if (dia > 0) {
//                result += ", ";
//            }
//            if (hora == 1) {
//                result += hora + " hora";
//            } else {
//                result += hora + " horas";
//            }
//        }
//        if (minutos > 0) {
//            if (hora > 0 || dia > 0) {
//                result += ", ";
//            }
//            result += minutos + " min";
//        }
//        return result;
//    }
    public static String formatDtBr(Date date) {
        return formatDtBr.format(date);
    }

    public static String formatHHmm(Date date) {
        return formatHHmm.format(date);
    }

    public static int getHoraDoDia(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutos(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSegundos(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static int getAno(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     *
     * @param xmlDate
     * @return
     */
    public static String getStringDate(final XMLGregorianCalendar xmlDate) {
        return formatBra1.format(convXMLToDate(xmlDate));
    }

    /**
     * Receive the param format EEE MMM dd HH:mm:ss ZZZZ yyyy and Return the
     * value Date object
     *
     * @param param EEE MMM dd HH:mm:ss ZZZZ yyyy
     * @return Date
     */
    public static Date parseParamToDate(String param) {
        Date result = null;
        try {
            if (param != null && !param.isEmpty() && !param.equals("null")) {
                result = formatForParser.parse(param);
            }
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
        }
        return result;
    }

    public static String getStringDateLn(final XMLGregorianCalendar xmlDate) {
        return formatLn.format(convXMLToDate(xmlDate));
    }

    public static String getTimeDifferenceBetweenTwoDates(String dateInicial, String dateFim) {
        String result;
        // Quantidade de milissegundos em um dia
        int tempoDia = 1000 * 60 * 60 * 24;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateInicial);
            d2 = format.parse(dateFim);
        } catch (ParseException e) {
            e.printStackTrace(System.err);
        }

        long diferenca = d2.getTime() - d1.getTime();
        //long diasDiferenca = diferenca / tempoDia;

        long diferencaSeconds = diferenca / 1000 % 60;
        long diferencaMinutes = diferenca / (60 * 1000) % 60;
        long diferencaHours = diferenca / (60 * 60 * 1000) % 24;
        long diferencaDays = diferenca / (24 * 60 * 60 * 1000);

        String horas;

        if (diferencaDays > 0) {
            horas = diferencaDays + " d " + diferencaHours + ":" + diferencaMinutes + ":" + diferencaSeconds;
        } else {
            horas = diferencaHours + ":" + diferencaMinutes + ":" + diferencaSeconds;
        }
        result = horas;

        return result;
    }

    public static String formatMilisegundosToDateBr(long tempoMilisegundos) {

        String result;

        long Seconds = tempoMilisegundos / 1000 % 60;
        long Minutes = tempoMilisegundos / (60 * 1000) % 60;
        long Hours = tempoMilisegundos / (60 * 60 * 1000) % 24;
        long Days = tempoMilisegundos / (24 * 60 * 60 * 1000);

        if (Days > 0) {
            result = Days + " d " + Hours + ":" + Minutes + ":" + Seconds;
        } else {
            result = Hours + ":" + Minutes + ":" + Seconds;
        }

        return result;
    }

    public static long getTimeDifferenceBetweenTwoDatesInMilliSeconds(String dateInicial, String dateFim) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateInicial);
            d2 = format.parse(dateFim);
        } catch (ParseException e) {
            e.printStackTrace(System.err);
        }

        long diferenca = d2.getTime() - d1.getTime();

        return diferenca;
    }

    public static Calendar convXMLToCalendar(XMLGregorianCalendar gregData) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, gregData.getYear());
        calendar.set(Calendar.MONTH, gregData.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, gregData.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, gregData.getHour());
        calendar.set(Calendar.MINUTE, gregData.getMinute());
        calendar.set(Calendar.SECOND, gregData.getSecond());
        calendar.set(Calendar.MILLISECOND, gregData.getMillisecond());
        return calendar;
    }

    public static int getWeekOfYearFromDate(Date data) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static Calendar convDateToCalendar(Date gregData) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, gregData.getYear());
        calendar.set(Calendar.MONTH, gregData.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, gregData.getDay());
        calendar.set(Calendar.HOUR, gregData.getHours());
        calendar.set(Calendar.MINUTE, gregData.getMinutes());
        calendar.set(Calendar.SECOND, gregData.getSeconds());
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * metodo que retorna uma String com a data Inicial completa com o ano e o
     * mes que o usuario enviou.
     *
     * @param mes
     * @param ano
     * @return String yyyy-MM-dd
     */
    public static String getFirstDayOfCurrentMonth(String mes, final Integer ano) {
        final Integer mesInt = Integer.parseInt(TimeControl.getMonthInt(mes));

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ano);
        cal.set(Calendar.MONTH, mesInt - 1);
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dtInicio = sdf.format(cal.getTime());

        return dtInicio;
    }

    /**
     * metodo que retorna uma String com a data final completa com o ano e o mes
     * que o usuario enviou.
     *
     * @param mes
     * @param ano
     * @return String yyyy-MM-dd
     */
    public static String getLastDayOfCurrentMonth(String mes, final Integer ano) {

        final Integer mesInt = Integer.parseInt(TimeControl.getMonthInt(mes));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mesInt - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dtFinal = sdf.format(calendar.getTime());

        return dtFinal;
    }
}
