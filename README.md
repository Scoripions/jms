# jms
Java消息中间件入门

	1.为什么需要Java消息中间件？
		案例:老王的睡前故事...
		老王有两个女儿，两个女儿都爱听睡前故事。之后大女儿和二女儿分别去了外地读书，但是她们依然要听睡前故事，所以之后老王还是给她们讲故事，有的时候，可能电话打不通，可能是因为信号或者其他元素所导致的。那么如果按照老王一个一个给她们讲故事，会非常的累。这时候老王想起来微信公众号，他每天将故事写好在微信公众号里发布，然后让女儿们订阅他的内容，这样一来，老王就减轻了很多的负担，女儿们也可以在睡前自如的听老王将故事了... 这就引入了消息中间件的概念。

		消息件带来的好处：
			1.消息的解耦
			2.异步
			3.横向拓展
			4.安全可靠
			5.顺序的保证
			...
	2.什么是中间件?
		非底层系统软件，非业务应用软件，不是直接给最终用户用的，不能直接给用户带来价值的软件统称为中间件。
	3.什么是消息中间件？
		关注于数据的发送与接收，利用高效可靠的异步消息机制集成分布式系统
	了解：
		JMS即Java消息服务（Java Message Service）应用程序接口，是一个Java平台中关于面向消息中间件（MOM）的API，用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。Java消息服务是一个与具体平台无关的API，绝大多数MOM提供商都对JMS提供支持

		AMQP，即Advanced Message Queuing Protocol,一个提供统一消息服务的应用层标准高级消息队列协议,是应用层协议的一个开放标准,为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同的开发语言等条件的限制。Erlang中的实现有 RabbitMQ等。

		对比1：
		
		
    ![JMS_AMQP](https://github.com/Scoripions/jms/blob/master/src/image/JMS_AMQP.png)

		常见的消息中间价的对比：
			ActiveMQ:
			ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。ActiveMQ 是一个完全支持JMS1.1和J2EE 1.4规范的 JMS Provider实现，尽管JMS规范出台已经是很久的事情了，但是JMS在当今的J2EE应用中间仍然扮演着特殊的地位。

			RabbitMQ：
			MQ全称为Message Queue, 消息队列（MQ）是一种应用程序对应用程序的通信方法。应用程序通过读写出入队列的消息（针对应用程序的数据）来通信，而无需专用连接来链接它们。消息传递指的是程序之间通过在消息中发送数据进行通信，而不是通过直接调用彼此来通信，直接调用通常是用于诸如远程过程调用的技术。排队指的是应用程序通过 队列来通信。队列的使用除去了接收和发送应用程序同时执行的要求。其中较为成熟的MQ产品有IBM WEBSPHERE MQ等等

			Kafka:
			Kafka是由Apache软件基金会开发的一个开源流处理平台，由Scala和Java编写。Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者规模的网站中的所有动作流数据。 这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。Kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群来提供实时的消息。
		对比2：
    ![对比2](https://github.com/Scoripions/jms/blob/master/src/image/%E5%B8%B8%E7%94%A8%E4%B8%AD%E9%97%B4%E4%BB%B6%E5%AF%B9%E6%AF%94.png)
	4.JMS规范
		JMS相关概念：
			提供者：实现JMS规范的消息中间件服务器
			客户端：发送或接收消息的应用程序
			生产者/发布者：创建并发送消息的客户端
			消费者/订阅者：接收并处理消息的客户端
			消息:应用程序之间传递的数据内容
			消息模式：在客户端之间传递消息的方式，JMS中定义了主题和队列两种模式
				队列模式：
					客户端包含生产者和消费者
					队列中的消息只能被一个消费者消费
					消费者可以随时消费队列中的消息
				主题模式:
					客户端包含发布者和订阅者
					主题中的消息被所有订阅者消费
					消费者不能消费订阅之前就发送到主题中的消息
		JMS编码接口：
			ConnectionFactory 用于创建连接到消息中间件的连接工厂
			Connection 代表了应用程序和消息服务器之间的通信链路
			Destination 指消息发布和接收的地点，包括队列或主题
			Session 表示一个单线程的上下文，用于发送和接收消息
			MessageConsumer 由会话创建，接收发送到目标的消息
			MessageProducer 由会话创建，发送消息到目标
			Message 在消费者和生产者之间传送对象，消息头，一组消息属性，一个消息体
			



	5.JMS代码演练
		ActiveMQ的安装部署：
			https://blog.csdn.net/clj198606061111/article/details/38145597
		ActiveMQ的集群配置：
			为什么要对消息中间件集群？
				1.实现高可用，以排除单点故障引起的服务中断
				2.实现负载均衡，以提升效率为更多客户提供服务
			集群的方式：
				客户端集群：让多个消费者消费同一个队列
				Broker cluster:多个Broker之间同步消息
				Master Slave:实现高可用
				
