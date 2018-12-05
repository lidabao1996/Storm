package demo.wc;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
/**
 * 第一级组件，作为任务的spont组件，来采集数据。
 * 
 * @author lifang
 *
 */
public class WordCountSpout extends BaseRichSpout {
	private static final long serialVersionUID = -4849899455244491702L;
	
	String[] datas = { "I love Beijing", "I love China","Beijing is the capital of China" };
			
	//定义变量保存输出流
	SpoutOutputCollector mConCollector;

	@Override
	public void nextTuple() {

		int radmon = (new Random()).nextInt(3);
		String data = datas[radmon];

		// 把数据发送给下一个组件
		// 数据一定要遵循schema的结构
		System.out.println("采集的数据是：" + data);

		this.mConCollector.emit(new Values(data));

	}

	@Override
	public void open(Map arg0, TopologyContext arg1,
			SpoutOutputCollector conCollector) {
		// 相当于Spout初始化方法
		// SpoutOutputCollector 相当于是输出流
		this.mConCollector = conCollector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// 申请Tuple格式，是Schema
		declarer.declare(new Fields("sentence"));
	}

}
