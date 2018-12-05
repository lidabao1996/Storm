package demo.wc;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
/**
 * 二级组件，是bolt组件，用于单词的拆分。
 * @author lifang
 */
public class WordCountSplitBolt extends BaseRichBolt{
	private OutputCollector collector;
	
	@Override
	public void execute(Tuple tuple) {
		String data = tuple.getStringByField("sentence");
		String[] words = data.split(" ");
		
		for (String w : words) {
			collector.emit(new Values(w,1));
		}
		
	}

	@Override
	public void prepare(Map value, TopologyContext topologyContext, OutputCollector collector) {
		//初始化bolt
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word","count"));
	}

}
