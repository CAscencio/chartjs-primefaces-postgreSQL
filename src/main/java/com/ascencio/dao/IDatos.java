package com.ascencio.dao;

import java.util.List;

public interface IDatos<Entity> {

    public List<Entity> datosChartBar() throws Exception;

    public List<Entity> datosChartInteractive() throws Exception;

    public List<Entity> datosChartLine() throws Exception;

    public List<Entity> datosChartPie() throws Exception;

}
