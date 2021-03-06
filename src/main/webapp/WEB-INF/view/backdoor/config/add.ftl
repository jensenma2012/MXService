[@override name="title"]
	添加配置项
[/@override]

[@override name="subtitle"]
	添加配置项
[/@override]

[@override name="action"]
	save
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>配置key:</th>
		<td>
			<input type="text" name="key" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>配置value:</th>
		<td>
			<textarea name="value" class="textarea" maxlength="255"></textarea>
		</td>
	</tr>
	<tr>
		<th>配置描述:</th>
		<td>
			<textarea name="description" class="textarea" maxlength="255"></textarea>
		</td>
	</tr>
[/@override]

[@override name="rules"]
	key: {
		required:true,
		maxlength:64
	},
	value: {
		required:true,
		maxlength:255
	},
	description: {
		maxlength:255
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]