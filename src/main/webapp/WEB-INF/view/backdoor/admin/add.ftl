[@override name="title"]
	添加管理员
[/@override]

[@override name="subtitle"]
	添加管理员
[/@override]

[@override name="action"]
	save
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>用户名:</th>
		<td>
			<input type="text" name="username" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>权限:</th>
		<td>
			<select name="role.id">
				<option value="">--请选择--</option>
				[#list roles as role]
					<option value="${role.id!}">${role.name!}</option>
				[/#list]
			</select>
		</td>
	</tr>
[/@override]

[@override name="rules"]
	username: {
		required:true,
		maxlength:64
	},
	"role.id": {
		required:true
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]