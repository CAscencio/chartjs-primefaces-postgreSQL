package com.ascencio.dao;

import java.util.List;

public interface IDatos<F, L, M> {

    public List<F> datosChartBar() throws Exception;

    public List<M> datosChartInteractive() throws Exception;

    public List<M> datosChartLine() throws Exception;

    public List<M> datosChartPie() throws Exception;
    
    public List<L> datosChartDonut() throws Exception;

}
