package org.mroczkarobert.creditsuisse.service.impl;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.mroczkarobert.creditsuisse.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MetricsServiceImpl implements MetricsService {

	private static final String PREFIX = "meter.calls.creditSuisse.";
	
	@Autowired
	private GaugeService gaugeService;
	
	private DescriptiveStatistics stats = new DescriptiveStatistics();
	
	@Override
	public long start() {
		return System.currentTimeMillis();
	}

	@Override
	public void end(long startTime) {
		long diffrence = System.currentTimeMillis() - startTime;
		stats.addValue(diffrence);

		gaugeService.submit(PREFIX + "requestCount", stats.getN());
		gaugeService.submit(PREFIX + "average", stats.getMean());
		gaugeService.submit(PREFIX + "maxRequestTime", stats.getMax());
		gaugeService.submit(PREFIX + "minRequestTime", stats.getMin());
		gaugeService.submit(PREFIX + "percentile95", stats.getPercentile(0.95));
	}
}
