/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascencio.controller;

import com.ascencio.dao.DatosImpl;
import com.ascencio.model.LugarDefuncion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "donutController")
@SessionScoped
public class DonutController implements Serializable {

    private DonutChartModel donutModel;
    
    private DatosImpl datos;
    List<LugarDefuncion> listaLugarDefuncion;

    public DonutController() throws Exception {
        datos = new DatosImpl();
        listaLugarDefuncion = new ArrayList();
        this.createDonutModel();
    }

    public void createDonutModel() throws Exception {
        listaLugarDefuncion = datos.datosChartDonut();
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> cantidad = new ArrayList<>();
        List<String> lugar = new ArrayList<>();
        
        for (LugarDefuncion datos : listaLugarDefuncion) {
            cantidad.add(datos.getCANT());
            lugar.add(datos.getLUGAR());
        }
        
        dataSet.setData(cantidad);
        data.setLabels(lugar);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(2, 62, 125)");
        bgColors.add("rgb(0, 119, 182)");
        bgColors.add("rgb(0, 180, 216)");
        bgColors.add("rgb(144, 224, 239)");
        bgColors.add("rgb(202, 240, 248)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);

        //Opciones
        DonutChartOptions options = new DonutChartOptions();

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Fallecidos por lugar de Difunción");
        title.setFontSize(15);
        options.setTitle(title);
        donutModel.setOptions(options);

        donutModel.setData(data);
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public DatosImpl getDatos() {
        return datos;
    }

    public void setDatos(DatosImpl datos) {
        this.datos = datos;
    }
    
}
