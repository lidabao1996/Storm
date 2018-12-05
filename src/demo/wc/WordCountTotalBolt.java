package demo.wc;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordCountTotalBolt extends BaseRichBolt {
	private OutputCollector collector;

	//定义一个集合来保存结果
	private Map<String, Integer> result = new HashMap<String, Integer>();
	@Override
	public void execute(Tuple tuple) {
			
		String word = tuple.getStringByField("word");
		int count = tuple.getIntegerByField("count");
		
		if(result.containsKey(word)){
			int total = result.get(word);
			result.put(word, total+count);
		}else {
			//这个单词第一次出现
			result.put(word, count);
		}
		
		//打印在屏幕上
		System.out.println("统计的结果是: " + result);
		
		//把结果继续发给下一个组件
		this.collector.emit(new Values(word,count));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1,OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("word","total"));
	}

}
