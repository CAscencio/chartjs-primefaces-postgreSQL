package com.ascencio.controller;

import com.ascencio.dao.DatosImpl;
import com.ascencio.model.Mensual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

@Named(value = "pieController")
@ApplicationScoped
public class PieController implements Serializable {

    private DatosImpl datos;
    List<Mensual> listaMensual;

    private PieChartModel pieModel;

    public PieController() throws Exception {
        datos = new DatosImpl();
        listaMensual = new ArrayList();
        this.createPieModel();
    }

    private void createPieModel() throws Exception {
        listaMensual = datos.datosChartPie();
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        //Cargando datos
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> valores = new ArrayList<>();
        for (Mensual mensual : listaMensual) {
            valores.add(mensual.getCANTINF());
            valores.add(mensual.getCANTREC());
            valores.add(mensual.getCANTMUE());
        }
        dataSet.setData(valores);

        //Asignando colores
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);

        //Asignando detalle (label)
        List<String> tipo = new ArrayList<>();
        tipo.add("Infectados");
        tipo.add("Recuperados");
        tipo.add("Muertos");
        data.setLabels(tipo);

        //Opciones
        PieChartOptions options = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Resumen Total");
        options.setTitle(title);
        pieModel.setOptions(options);

        pieModel.setData(data);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
}
