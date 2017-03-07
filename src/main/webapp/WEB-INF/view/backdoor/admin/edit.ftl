[@override name="title"]
	编辑管理员
[/@override]

[@override name="subtitle"]
	编辑管理员
[/@override]

[@override name="action"]
	update
[/@override]

[@override name="form"]
	<tr>
		<th>用户名:</th>
		<td>
			${admin.username}
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>权限:</th>
		<td>
			<select name="role.id" class="select">
				<option value="">--请选择--</option>
				[#list roles as role]
					<option value="${role.id!}" [#if admin.role.id?? && admin.role.id==role.id]selected[/#if]>${role.name!}</option>
				[/#list]
			</select>
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>是否启用</th>
		<td>
			<input type="radio" name="enabled" value="true" [#if admin.enabled]checked[/#if] />是
			<input type="radio" name="enabled" value="false" [#if !admin.enabled]checked[/#if] />否
		</td>
	</tr>
[/@override]

[@override name="rules"]
	"role.id": {
		required:true
	},
	enabled: {
		required:true
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]