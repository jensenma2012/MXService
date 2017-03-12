[@override name="title"]
	编辑微信用户
[/@override]

[@override name="subtitle"]
	编辑微信用户
[/@override]

[@override name="action"]
	update
[/@override]

[@override name="form"]
	<tr>
		<th>微信id:</th>
		<td>
			${wechatuser.username}
		</td>
	</tr>
	<tr>
		<th>别名:</th>
		<td>
			<input type="text" name="alias" class="text" value="${wechatuser.alias}" maxlength="64" />
		</td>
	</tr>
[/@override]

[@override name="rules"]
	alias: {
		maxlength:64
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]