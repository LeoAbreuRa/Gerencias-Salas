package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.ReservaDao;
import br.com.container.dao.ReservaDaoImpl;
import br.com.container.modelo.GraficoReservaPorTotalMesSala;
import br.com.container.modelo.Reserva;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;


@ManagedBean(name = "graficoC")
@ViewScoped
public class GraficoControle {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private Reserva reserva;
    private ReservaDao reservaDao;
    private Session session;
    

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Gráfico de Salas");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        reservaDao = new ReservaDaoImpl();
        session = HibernateUtil.abreSessao();
        List<GraficoReservaPorTotalMesSala> gtr = reservaDao.totalMesSala(session);
        ChartSeries salas = new ChartSeries();
        salas.setLabel("Total de Salas");
        salas.set("Janeiro", gtr.get(0).getTotal());
        salas.set("Fevereiro", gtr.get(1).getTotal());
        salas.set("Março", gtr.get(2).getTotal());
        salas.set("Abril", gtr.get(3).getTotal());
        salas.set("Maio", gtr.get(4).getTotal());
        salas.set("Junho", gtr.get(5).getTotal());
        salas.set("Julho", gtr.get(6).getTotal());
        salas.set("Agosto", gtr.get(7).getTotal());
        salas.set("Setembro", gtr.get(8).getTotal());
        salas.set("Outubro", gtr.get(9).getTotal());
        salas.set("Novembro", gtr.get(10).getTotal());
        salas.set("Dezembro", gtr.get(11).getTotal());


        model.addSeries(salas);
        session.close();
        
        return model;
    }
    
    
    
}
