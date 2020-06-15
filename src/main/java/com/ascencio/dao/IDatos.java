package com.ascencio.dao;

import java.util.List;

public interface IDatos<C, E> {

    public List<E> datosChartBar() throws Exception;

    public List<C> datosChartInteractive() throws Exception;

    public List<C> datosChartLine() throws Exception;

    public List<C> datosChartPie() throws Exception;

}
