/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.faces.constants;

/**
 *
 * @author ioliveira
 */
public class StatusSiso {

    /**
     * ESTES SAO OS IDS DO TBTIPOSTATUSPROCEDIMENTO PARA OS PROCEDIMENTOS.
     */
    public static final int ID_STATUS_PROCEDIMENTO_NAO_REALIZADOS = 1;
    public static final int ID_STATUS_PROCEDIMENTO_REALIZADOS = 2;
    public static final int ID_STATUS_PROCEDIMENTO_EM_TRATAMENTO = 3;
    public static final int ID_STATUS_PROCEDIMENTO_GERAL = 4;
    public static final int ID_STATUS_PROCEDIMENTO_NAO_LANCADOS = 5;
    public static final int ID_STATUS_PROCEDIMENTO_CANCELADOS = 6;
    public static final int ID_STATUS_PROCEDIMENTO_REMOVIDOS = 7;

    /**
     * ESTES SAO OS INTIPOSTATUSPROCEDIMENTO PARA OS PROCEDIMENTOS.
     */
    public static final int INT_INTIPOSTATUS_PROCEDIMENTO_NAO_REALIZADOS = 0;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_REALIZADOS = 1;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_EM_TRATAMENTO = 2;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_GERAL = 3;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_NAO_LANCADOS = 4;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_CANCELADOS = 5;
    public static final int INT_INTIPO_STATUS_PROCEDIMENTO_REMOVIDOS = 6;

    /**
     * ESTES SAO OS IDS DO TBTIPOSTATUSAGENDAMENTO PARA OS AGENDAMENTOS.
     */
    public static final int ID_STATUS_AGENDAMENTO_AGENDADO = 1;
    public static final int ID_STATUS_AGENDAMENTO_PRESENTE = 2;
    public static final int ID_STATUS_AGENDAMENTO_ATENDIDO = 3;
    public static final int ID_STATUS_AGENDAMENTO_AUSENTE = 4;
    public static final int ID_STATUS_AGENDAMENTO_FALTOSO = 5;

    /**
     * ESTES SAO OS INTIPOSTATUSAGENDAMENTO PARA OS AGENDAMENTOS.
     */
    public static final int INT_INTIPO_STATUS_AGENDAMENTO_AGENDADO = 1;
    public static final int INT_INTIPO_STATUS_AGENDAMENTO_PRESENTE = 2;
    public static final int INT_INTIPO_STATUS_AGENDAMENTO_ATENDIDO = 3;
    public static final int INT_INTIPO_STATUS_AGENDAMENTO_AUSENTE = 4;
    public static final int INT_INTIPO_STATUS_AGENDAMENTO_FALTOSO = 5;

    /**
     * ESTES SAO OS INTEIROS QUE REPRESENTAM AS FACES DOS DENTES.
     */
    public static final int IN_FACE_DENTE_RAIZ = 10;
    public static final int IN_FACE_DENTE_CERVICAL = 1;
    public static final int IN_FACE_DENTE_VESTIBULAR = 14;
    public static final int IN_FACE_DENTE_DISTAL = 3;
    public static final int IN_FACE_DENTE_OCLUSAL = 8;
    public static final int IN_FACE_DENTE_MESIAL = 7;
    public static final int IN_FACE_DENTE_PALATINA = 9;

    /**
     * ESTES SAO OS IDS DO TBTIPOSTATUSORCAMENTO PARA OS ORCAMENTO.
     */
    public static final int ID_STATUS_ORCAMENTO_ABERTO = 1;
    public static final int ID_STATUS_ORCAMENTO_FECHADO = 2;
    public static final int ID_STATUS_ORCAMENTO_CANCELADO = 3;

    /**
     * ESTES SAO OS INTIPOSTATUSORCAMENTO DO TBTIPOSTATUSORCAMENTO PARA OS
     * ORCAMENTO.
     */
    public static final int INT_INTIPO_STATUS_ORCAMENTO_ABERTO = 0;
    public static final int INT_INTIPO_STATUS_ORCAMENTO_FECHADO = 1;
    public static final int INT_INTIPO_STATUS_ORCAMENTO_CANCELADO = 2;
}
