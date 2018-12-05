package demo.wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountTopology {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();
		
		//���������spout���
		builder.setSpout("wordcount_spout", new WordCountSpout());
		//��������ĵ��ʲ�ֵ�bolt���,���������
		builder.setBolt("wordcount_split", new WordCountSplitBolt()).shuffleGrouping("wordcount_spout");
		//��������ĵ��ʼ�����bolt������ǰ��ֶη���
		builder.setBolt("wordcount_total",new WordCountTotalBolt()).fieldsGrouping("wordcount_split", new Fields("word"));
		//����һ������Topology
		StormTopology topology = builder.createTopology();
		//����һ��Config���󣬱���������Ϣ
		Config conf = new Config();
		
		//�ύstorm�����������ַ�ʽ
		
		//1:�����ύ
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("MyWordCount", conf, topology);
		//2:��Ⱥģʽ
		
		
	}
	
	

}
