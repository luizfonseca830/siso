/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.constants;

/**
 *
 * @author ioliveira
 */
public final class PagesUrl {

    private PagesUrl() {
    }
    public static final String EXT_URL = ".jsf";
    public static final String URL_HOME = "/faces/index" + EXT_URL;
    public static final String URL_LOGIN = "/faces/login" + EXT_URL;
    public static final String URL_USUARIO_LIST = "/faces/cadastrosadministrativos/usuario/usuarioList" + EXT_URL;
    public static final String URL_CONSULTAR_USER = "/faces/cadastrosadministrativos/usuario/consultarUser" + EXT_URL;
    public static final String URL_EDITAR_USER = "/faces/cadastrosadministrativos/usuario/editarUser" + EXT_URL;
    public static final String URL_MENU_LIST = "/faces/cadastrosadministrativos/funcionalidade/menuList" + EXT_URL;
    public static final String URL_MENU_CONSULTA = "/faces/cadastrosadministrativos/funcionalidade/menuConsulta" + EXT_URL;
    public static final String URL_USER_NOT_AUTHORIZED = "/public/error/errorNotAuthorized" + EXT_URL;
    public static final String URL_USER_PADRAO = "/faces/cadastrosadministrativos/usuario/meusDados" + EXT_URL;
    public static final String URL_MODULO_LIST = "/faces/cadastrosadministrativos/modulo/ModuloList" + EXT_URL;
    public static final String URL_PERFIL_LIST = "/faces/cadastrosadministrativos/perfil/PerfilList" + EXT_URL;
    public static final String URL_CLIENTE_LIST = "/faces/cadastros/cliente/clienteList" + EXT_URL;
    public static final String URL_BAIRRO_LIST = "/faces/cadastros/bairro/bairroList" + EXT_URL;
    public static final String URL_AGENDAMENTO_LIST = "/faces/cadastros/agendamento/agendamentoList" + EXT_URL;
    public static final String URL_ATENDIMENTO_LIST = "/faces/atendimento/atendimentoList" + EXT_URL;
    public static final String URL_FUNCIONARIO_LIST = "/faces/cadastros/funcionario/funcionarioList" + EXT_URL;
    public static final String URL_CIDADE_LIST = "/faces/cadastros/cidade/cidadeList" + EXT_URL;
    public static final String URL_SERVICO_LIST = "/faces/cadastros/servico/servicoList" + EXT_URL;
}