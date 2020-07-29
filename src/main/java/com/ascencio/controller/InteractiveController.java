package com.ascencio.controller;

import com.ascencio.dao.DatosImpl;
import com.ascencio.model.Mensual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "interactiveController")
@ApplicationScoped
public class InteractiveController implements Serializable {

    private DatosImpl datos;
    List<Mensual> listaMensual;

    private BarChartModel barModel2;

    public InteractiveController() throws Exception {
        datos = new DatosImpl();
        listaMensual = new ArrayList();
        this.createBarModel2();
    }

    public void createBarModel2() throws Exception {
        listaMensual = datos.datosChartInteractive();
        barModel2 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barInfectados = new BarChartDataSet();
        barInfectados.setLabel("Infectados");
        barInfectados.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        barInfectados.setBorderColor("rgb(255, 99, 132)");
        barInfectados.setBorderWidth(1);

        BarChartDataSet barRecuperados = new BarChartDataSet();
        barRecuperados.setLabel("Recuperados");
        barRecuperados.setBackgroundColor("rgba(54, 162, 235, 0.2)");
        barRecuperados.setBorderColor("rgb(54, 162, 235)");
        barRecuperados.setBorderWidth(1);

        BarChartDataSet barMuertos = new BarChartDataSet();
        barMuertos.setLabel("Muertos");
        barMuertos.setBackgroundColor("rgba(255, 205, 86, 0.2)");
        barMuertos.setBorderColor("rgb(255, 205, 86)");
        barMuertos.setBorderWidth(1);

        //Listas de datos para las barras
        List<Number> infectados = new ArrayList<>();
        List<Number> recuperados = new ArrayList<>();
        List<Number> muertos = new ArrayList<>();
        List<String> meses = new ArrayList<>();

        //Agregando datos a las listas correspondientes
        for (Mensual mensual : listaMensual) {
            infectados.add(mensual.getCANTINF());
            recuperados.add(mensual.getCANTREC());
            muertos.add(mensual.getCANTMUE());
            meses.add(mensual.getMESMEN());
        }
        barInfectados.setData(infectados);
        barRecuperados.setData(recuperados);
        barMuertos.setData(muertos);

        data.addChartDataSet(barInfectados);
        data.addChartDataSet(barRecuperados);
        data.addChartDataSet(barMuertos);
        data.setLabels(meses);
        barModel2.setData(data);

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
        title.setText("Datos por mes");
        title.setFontSize(15);
        options.setTitle(title);

        barModel2.setOptions(options);
    }

    public DatosImpl getDatos() {
        return datos;
    }

    public void setDatos(DatosImpl datos) {
        this.datos = datos;
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

}
