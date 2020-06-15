package com.ascencio.dao;

import com.ascencio.model.Mensual;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatosImpl extends ConexionDB implements IDatos<Mensual> {

    @Override
    public List<Mensual> datosChartBar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
