package software.ulpgc.moneyCalculator.swing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import software.ulpgc.moneyCalculator.TimeSeries;
import software.ulpgc.moneyCalculator.TimeSeriesDisplay;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SwingTimeSeriesDisplay extends JPanel implements TimeSeriesDisplay {

    @Override
    public void show(TimeSeries timeSeries, String[] dateLabels) {
        JFreeChart chart = chartOf(timeSeries, dateLabels);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        this.removeAll(); // Elimina los componentes anteriores (si los hubiera)
        this.add(chartPanel);
        this.validate(); // Valida el panel después de agregar el nuevo componente
    }

    private JFreeChart chartOf(TimeSeries timeSeries, String[] dateLabels) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        org.jfree.data.time.TimeSeries series = new org.jfree.data.time.TimeSeries("Exchange Rate");

        // Asumiendo que 'data' es un array de valores y 'dateLabels' es un array de fechas en formato String
        for (int i = 0; i < timeSeries.data().length; i++) {
            LocalDate date = LocalDate.parse(dateLabels[i]);
            Date actualDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            series.add(new Day(actualDate), timeSeries.data()[i]);
        }

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                timeSeries.title(),
                timeSeries.xAxis(),
                timeSeries.yAxis(),
                dataset,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = new XYBarRenderer();
        plot.setRenderer(renderer);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        axis.setVerticalTickLabels(true);

        return chart;
    }
}