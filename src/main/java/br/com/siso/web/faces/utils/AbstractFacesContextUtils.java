/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

/**
 *
 * @author ioliveira
 */
public abstract class AbstractFacesContextUtils {

    public static void addMessageInfo(String strMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, strMsg));
    }

    public static void addMessageWarn(String strMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, strMsg));
    }

    public static void addMessageError(String strMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, strMsg));
    }

    public static void addMessageFatal(String strMsg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, null, strMsg));
    }

    public static void redirectPage(String strPath) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Shareds.PROJECT_DIRECTORY + strPath);
        } catch (IOException ex) {
            Logger.getLogger(AbstractFacesContextUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void redirectPageWithParam(String strPath, Map<String, Object> hash) {
        StringBuilder param = new StringBuilder();
        param.append("?");
        for (Map.Entry<String, Object> entry : hash.entrySet()) {
            param.append(entry.getKey());
            param.append("=");
            param.append(entry.getValue());
            param.append("&");
        }
        param.deleteCharAt(param.length() - 1);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(Shareds.PROJECT_DIRECTORY + strPath + param.toString());
        } catch (IOException ex) {
            Logger.getLogger(AbstractFacesContextUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getParamString(String strParam) {
        String result;
        try {
            result = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(strParam);
            if (result == null) {
                result = "";
            }
        } catch (NullPointerException e) {
            result = "";
        } catch (NumberFormatException n) {
            result = "";
        } catch (Exception e) {
            e.printStackTrace(System.err);
            result = "";
        }
        return result;
    }

    public static int getParamInt(String strParam) {
        int result = -1;
        try {
            result = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(strParam));
        } catch (NullPointerException n) {
        } catch (NumberFormatException n) {

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return result;
    }

//    public static Date getParamDate(String strParam) {
//        Date result = null;
//
//        try {
//            Object objectParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(strParam);
//            if (objectParam != null) {
//                result = ConvData.parseParamToDate(objectParam.toString());
//            }
//        } catch (Exception e) {
//            result = null;
//        }
//        return result;
//    }
    public static Object getSessionValue(String strSessionBean) {
        ExternalContext tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        Map sMap = tmpEC.getSessionMap();
        return sMap.get(strSessionBean);
    }

    public static void setCookie(String cookieName, String cookieValue) {
        Map<String, Object> cookieProps = new HashMap<String, Object>();
        cookieProps.put("maxAge", new Integer(365 * 24 * 60 * 60)); // 1 year as default
        cookieProps.put("path", FacesContext.getCurrentInstance()
                .getExternalContext().getRequestServerName());
        FacesContext.getCurrentInstance().getExternalContext()
                .addResponseCookie(cookieName, cookieValue, cookieProps);
    }

    public static String getCookie(String tag) {
        String value = "";
        try {
            Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
            Cookie cookie = (Cookie) cookies.get(tag);
            value = cookie.getValue();
        } catch (Exception e) {
            //e.printStackTrace(System.err);
        }
        return value;
    }

//    public static String doUpload(UploadedFile uploadedFile, String caminho, String nomeArquivo) throws Exception {
//        String filePath = "";
//
//        File file = new File(caminho, nomeArquivo);
//
//        try {
//            FileOutputStream fileOutput = new FileOutputStream(file);
//            fileOutput.write(IOUtils.toByteArray(uploadedFile.getInputstream()));
//            fileOutput.flush();
//            fileOutput.close();
//            filePath = file.getName();
//        } catch (FileNotFoundException ex) {
//            throw new FileNotFoundException(Resources.getMessage("falhaaoencontraroarquivo"));
//        } catch (IOException ex) {
//            throw new IOException(Resources.getMessage("falhaaoabriroarquivo"));
//        }
//        return filePath;
//    }
    public static boolean doMove(String caminho_arq, String novo_pasta) {

        boolean result = true;
        File filearq = new File(caminho_arq);
        File filedir = new File(novo_pasta);
        if (filearq.exists()) {
            filearq.renameTo(new File(filedir, filearq.getName()));
        } else {
            result = false;
        }

        return result;
    }

    public static void doCopyFile(String localAtual, String localDestino) {
        InputStream inStream;
        OutputStream outStream;

        try {

            File sourceFile = new File(localAtual);
            File destFile = new File(localDestino);

            inStream = new FileInputStream(sourceFile);
            outStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void renameFile(String caminhoArquivo, String oldName, String newName) {

        File oldfile = new File(caminhoArquivo + "/" + oldName);
        File newfile = new File(caminhoArquivo + "/" + newName);
        oldfile.renameTo(newfile);

    }
}
