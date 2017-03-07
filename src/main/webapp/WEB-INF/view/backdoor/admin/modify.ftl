[@override name="title"]
	密码重置
[/@override]

[@override name="subtitle"]
	密码重置
[/@override]

[@override name="action"]
	resetPassword
[/@override]

[@override name="form"]
	<tr>
		<th>账号名称:</th>
		<td>
			${username}
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>原密码:</th>
		<td>
			<input type="password" name="password" class="text" maxlength="32" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>新密码:</th>
		<td>
			<input type="password" name="newPassword" id="newPassword" class="text" maxlength="32" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>确认新密码:</th>
		<td>
			<input type="password" name="rePassword" class="text" maxlength="32" />
		</td>
	</tr>
[/@override]

[@override name="rules"]
	password: {
		required:true,
		pattern:/^[^\s&\"<>]+$/,
		minlength:4,
		maxlength:32
	},
	newPassword: {
		required:true,
		pattern:/^[^\s&\"<>]+$/,
		minlength:4,
		maxlength:32
	},
	rePassword: {
		required:true,
		equalTo:"#newPassword"
	}
[/@override]

[@override name="messages"]
	newPassword: {
		pattern:"包含非法字符！"
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]