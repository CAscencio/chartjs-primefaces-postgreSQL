package com.ascencio.controller;

import com.ascencio.dao.DatosImpl;
import com.ascencio.model.Fallecido;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "barController")
@SessionScoped
public class BarController implements Serializable {

    private DatosImpl datos;
    List<Fallecido> listaFallecidos;

    private BarChartModel barModel;

    public BarController() throws Exception {
        datos = new DatosImpl();
        listaFallecidos = new ArrayList();
        this.createBarModel();
    }

    public void createBarModel() throws Exception {
        listaFallecidos = datos.datosChartBar();
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Fallecidos");

        //Cargando datos
        List<String> labels = new ArrayList<>();
        List<Number> valores = new ArrayList<>();
        
        for (Fallecido fallecido : listaFallecidos) {
            labels.add(fallecido.getTIPFALL());
            valores.add(fallecido.getCANTFALL());
        }

        data.setLabels(labels);
        barDataSet.setData(valores);

        //Definiendo colores para las barras
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 159, 64)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        barModel.setData(data);

        //Opciones
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Fallecidos por Etapa de Vida");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#909090");
        legendLabels.setFontSize(12);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public DatosImpl getDatos() {
        return datos;
    }

    public void setDatos(DatosImpl datos) {
        this.datos = datos;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
