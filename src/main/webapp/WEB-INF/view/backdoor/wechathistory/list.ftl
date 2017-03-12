[@override name="title"]
	微信消息列表
[/@override]

[@override name="subtitle"]
	微信消息列表
[/@override]

[@override name="columntitle"]
	<th>
		<a href="javascript:" class="sort" name="type">消息类型</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="content">消息内容</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="wechatUser.username">微信id</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="wechatUser.alias">用户别名</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="createDate">创建时间</a>
	</th>
[/@override]

[@override name="columnvalue"]
	<td>
		${list.type}
	</td>
	<td>
		${list.content}
	</td>
	<td>
		${list.wechatUser.username}
	</td>
	<td>
		${list.wechatUser.alias}
	</td>
	<td>
		${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
	</td>
[/@override]

[@extends name="backdoor/base/table.ftl" /]