package com.ascencio.dao;

import com.ascencio.model.Fallecido;
import com.ascencio.model.LugarDefuncion;
import com.ascencio.model.Mensual;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatosImpl extends ConexionDB implements IDatos<Fallecido, LugarDefuncion, Mensual> {

    @Override
    public List<Fallecido> datosChartBar() throws Exception {
        this.conectar();
        List<Fallecido> listFallecidos;
        Fallecido fallecido;
        String sql = "SELECT * FROM public.\"FALLECIDOS_EV\"";
        try {
            listFallecidos = new ArrayList();
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                fallecido = new Fallecido();
                fallecido.setIDFALL(rs.getInt("IDFALL"));
                fallecido.setTIPFALL(rs.getString("TIPFALL"));
                fallecido.setCANTFALL(rs.getInt("CANTFALL"));
                listFallecidos.add(fallecido);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listFallecidos;
    }

    @Override
    public List<Mensual> datosChartInteractive() throws Exception {
        this.conectar();
        List<Mensual> listMensual;
        Mensual mensual;
        String sql = "SELECT * FROM public.\"MENSUAL\"";
        try {
            listMensual = new ArrayList();
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mensual = new Mensual();
                mensual.setIDMEN(rs.getInt("IDMEN"));
                mensual.setMESMEN(rs.getString("MESMEN"));
                mensual.setCANTINF(rs.getInt("CANTINF"));
                mensual.setCANTREC(rs.getInt("CANTREC"));
                mensual.setCANTMUE(rs.getInt("CANTMUE"));
                listMensual.add(mensual);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listMensual;
    }

    @Override
    public List<Mensual> datosChartLine() throws Exception {
        this.conectar();
        List<Mensual> listMensual;
        Mensual mensual;
        String sql = "SELECT \n"
                + "	\"MESMEN\",\n"
                + "	\"CANTINF\"\n"
                + "FROM public.\"MENSUAL\"";
        try {
            listMensual = new ArrayList();
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mensual = new Mensual();
                mensual.setMESMEN(rs.getString("MESMEN"));
                mensual.setCANTINF(rs.getInt("CANTINF"));
                listMensual.add(mensual);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listMensual;
    }

    @Override
    public List<Mensual> datosChartPie() throws Exception {
        this.conectar();
        List<Mensual> listMensual;
        Mensual mensual;
        String sql = "SELECT \n"
                + "	SUM(\"CANTINF\") AS CANTINF,\n"
                + "	SUM(\"CANTREC\") AS CANTREC,\n"
                + "	SUM(\"CANTMUE\") AS CANTMUE\n"
                + "FROM public.\"MENSUAL\"";
        try {
            listMensual = new ArrayList();
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mensual = new Mensual();
                mensual.setCANTINF(rs.getInt("CANTINF"));
                mensual.setCANTREC(rs.getInt("CANTREC"));
                mensual.setCANTMUE(rs.getInt("CANTMUE"));
                listMensual.add(mensual);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listMensual;
    }

    @Override
    public List<LugarDefuncion> datosChartDonut() throws Exception {
        this.conectar();
        List<LugarDefuncion> listLugarDefuncion;
        LugarDefuncion lugarDefuncion;
        String sql = "SELECT * FROM public.\"FL_DEFUNCION\"";
        try {
            listLugarDefuncion = new ArrayList();
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lugarDefuncion = new LugarDefuncion();
                lugarDefuncion.setLUGAR(rs.getString("LUGAR"));
                lugarDefuncion.setCANT(rs.getInt("CANT"));
                listLugarDefuncion.add(lugarDefuncion);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listLugarDefuncion;
    }
}
