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
    private LineChartModel lineModel;

    public LineController() throws Exception {
        datos = new DatosImpl();
        listaMensual = new ArrayList();
        this.createLineModel();
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

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

}
