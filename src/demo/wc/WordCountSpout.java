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
 * ��һ���������Ϊ�����spont��������ɼ����ݡ�
 * 
 * @author lifang
 *
 */
public class WordCountSpout extends BaseRichSpout {
	private static final long serialVersionUID = -4849899455244491702L;
	
	String[] datas = { "I love Beijing", "I love China","Beijing is the capital of China" };
			
	//����������������
	SpoutOutputCollector mConCollector;

	@Override
	public void nextTuple() {

		int radmon = (new Random()).nextInt(3);
		String data = datas[radmon];

		// �����ݷ��͸���һ�����
		// ����һ��Ҫ��ѭschema�Ľṹ
		System.out.println("�ɼ��������ǣ�" + data);

		this.mConCollector.emit(new Values(data));

	}

	@Override
	public void open(Map arg0, TopologyContext arg1,
			SpoutOutputCollector conCollector) {
		// �൱��Spout��ʼ������
		// SpoutOutputCollector �൱���������
		this.mConCollector = conCollector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// ����Tuple��ʽ����Schema
		declarer.declare(new Fields("sentence"));
	}

}
