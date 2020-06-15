package com.ascencio.controller;

import com.ascencio.dao.DatosImpl;
import com.ascencio.model.Mensual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "lineController")
@ApplicationScoped
public class LineController implements Serializable {

    private DatosImpl datos;
    List<Mensual> listaMensual;
    private LineChartModel lineModel, lineModel2;

    public LineController() throws Exception {
        datos = new DatosImpl();
        listaMensual = new ArrayList();
        this.createLineModel();
        this.createLineModel2();
    }

    public void createLineModel() throws Exception {
        listaMensual = datos.datosChartLine();
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        //Cargando datos
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Number> valores = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (Mensual mensual : listaMensual) {
            valores.add(mensual.getCANTINF());
            labels.add(mensual.getMESMEN());
        }

        dataSet.setData(valores);
        dataSet.setFill(false);
        dataSet.setLabel("Casos Infectados");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        data.setLabels(labels);

        //Opciones
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Número de casos activos");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    public void createLineModel2() throws Exception {
        listaMensual = datos.datosChartLine();
        lineModel2 = new LineChartModel();
        ChartData data = new ChartData();

        //Cargando datos
        LineChartDataSet dataSet = new LineChartDataSet();
        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Number> valores = new ArrayList<>();
        List<Number> valores2 = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (Mensual mensual : listaMensual) {
            valores.add(mensual.getCANTINF());
            valores2.add(mensual.getCANTINF() + 8);
            labels.add(mensual.getMESMEN());
        }

        dataSet.setData(valores);
        dataSet.setFill(false);
        dataSet.setLabel("Hombres");
        dataSet.setBorderColor("rgb(0, 180, 216)");
        dataSet.setLineTension(0.1);

        dataSet2.setData(valores2);
        dataSet2.setFill(false);
        dataSet2.setLabel("Mujeres");
        dataSet2.setBorderColor("rgb(255, 73, 158)");
        dataSet2.setLineTension(0.1);

        data.addChartDataSet(dataSet);
        data.addChartDataSet(dataSet2);

        data.setLabels(labels);

        //Opciones
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Número de casos activos");
        options.setTitle(title);

        lineModel2.setOptions(options);
        lineModel2.setData(data);
    }


    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }

    public DatosImpl getDatos() {
        return datos;
    }

    public void setDatos(DatosImpl datos) {
        this.datos = datos;
    }
}
