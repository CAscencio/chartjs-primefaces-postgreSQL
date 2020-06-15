/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ascencio.controller;

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

    public DonutController() {
        this.createDonutModel();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(24);
        values.add(14);
        values.add(12);
        values.add(9);
        values.add(8);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(2, 62, 125)");
        bgColors.add("rgb(0, 119, 182)");
        bgColors.add("rgb(0, 180, 216)");
        bgColors.add("rgb(144, 224, 239)");
        bgColors.add("rgb(202, 240, 248)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("MINSA");
        labels.add("ESSALUD");
        labels.add("DOMICILIO");
        labels.add("PNP");
        labels.add("CLINICA PRIVADA");
        data.setLabels(labels);

        //Opciones
        DonutChartOptions options = new DonutChartOptions();

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Fallecidos por lugar de Difunción");
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

}
