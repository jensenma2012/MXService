[@override name="title"]
	编辑权限
[/@override]

[@override name="extracss"]
	<style type="text/css">
	    .authorities label {
			min-width: 120px;
			_width: 120px;
			display: block;
			float: left;
			padding-right: 4px;
			_white-space: nowrap;
		}
	
		.authorities a{
		   color:#488BD1;
		   font-weight: bold;
		}
	</style>
[/@override]

[@override name="subtitle"]
	编辑权限
[/@override]

[@override name="action"]
	update
[/@override]

[@override name="form"]
	<input type="hidden" name="authorities" value="ROLE_BASE" />
	<tr>
		<th>权限名:</th>
		<td>
			${role.name}
		</td>
	</tr>
	<tr>
		<th>权限描述:</th>
		<td>
			<input type="text" name="description" class="text" value="${role.description}" maxlength="64" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			&nbsp;
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">歌手</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="artist" name="authorities" value="ROLE_ARTIST" [#if role.authorityList?contains("ROLE_ARTIST")]checked[/#if] />歌手管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet artist">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ARTIST_ADD" [#if role.authorityList?contains("ROLE_ARTIST_ADD")]checked[/#if] />添加歌手
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ARTIST_EDIT" [#if role.authorityList?contains("ROLE_ARTIST_EDIT")]checked[/#if] />编辑歌手
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">专辑</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="album" name="authorities" value="ROLE_ALBUM" [#if role.authorityList?contains("ROLE_ALBUM")]checked[/#if] />专辑管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet album">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ALBUM_ADD" [#if role.authorityList?contains("ROLE_ALBUM_ADD")]checked[/#if] />添加专辑
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ALBUM_EDIT" [#if role.authorityList?contains("ROLE_ALBUM_EDIT")]checked[/#if] />编辑专辑
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ALBUM_DELETE" [#if role.authorityList?contains("ROLE_ALBUM_DELETE")]checked[/#if] />删除专辑
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">歌曲</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="music" name="authorities" value="ROLE_MUSIC" [#if role.authorityList?contains("ROLE_MUSIC")]checked[/#if] />歌曲管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet music">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_MUSIC_ADD" [#if role.authorityList?contains("ROLE_MUSIC_ADD")]checked[/#if] />添加歌曲
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_MUSIC_EDIT" [#if role.authorityList?contains("ROLE_MUSIC_EDIT")]checked[/#if] />编辑歌曲
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_MUSIC_REFRESH" [#if role.authorityList?contains("ROLE_MUSIC_REFRESH")]checked[/#if] />同步服务器歌曲
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_MUSIC_DELETE" [#if role.authorityList?contains("ROLE_MUSIC_DELETE")]checked[/#if] />删除歌曲
				</label>
			</span>
		</td>
	</tr>
    <tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">微信</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="wechat" name="authorities" value="ROLE_WECHATUSER" [#if role.authorityList?contains("ROLE_WECHATUSER")]checked[/#if] />用户管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet wechat">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_WECHATUSER_EDIT" [#if role.authorityList?contains("ROLE_WECHATUSER_EDIT")]checked[/#if] />编辑用户
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_WECHATHISTORY" [#if role.authorityList?contains("ROLE_WECHATHISTORY")]checked[/#if] />消息管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">管理员</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="admin" name="authorities" value="ROLE_ADMIN" [#if role.authorityList?contains("ROLE_ADMIN")]checked[/#if] />管理员管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet admin">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ADMIN_ADD" [#if role.authorityList?contains("ROLE_ADMIN_ADD")]checked[/#if] />添加管理员
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ADMIN_EDIT" [#if role.authorityList?contains("ROLE_ADMIN_EDIT")]checked[/#if] />编辑管理员
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">权限</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="role" name="authorities" value="ROLE_ROLE" [#if role.authorityList?contains("ROLE_ROLE")]checked[/#if] />权限管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet role">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ROLE_ADD" [#if role.authorityList?contains("ROLE_ROLE_ADD")]checked[/#if] />添加权限
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_ROLE_EDIT" [#if role.authorityList?contains("ROLE_ROLE_EDIT")]checked[/#if] />编辑权限
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>
			<a href="javascript:;" class="selectAll" title="全选此组权限">配置</a>
		</th>
		<td>
			<span class="fieldSet">
				<label>
					<input type="checkbox" id="config" name="authorities" value="ROLE_CONFIG" [#if role.authorityList?contains("ROLE_CONFIG")]checked[/#if] />配置项管理
				</label>
			</span>
		</td>
	</tr>
	<tr class="authorities">
		<th>&nbsp;</th>
		<td>
			<span class="fieldSet config">
				<label>
					<input type="checkbox" name="authorities" value="ROLE_CONFIG_ADD" [#if role.authorityList?contains("ROLE_CONFIG_ADD")]checked[/#if] />添加配置项
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_CONFIG_EDIT" [#if role.authorityList?contains("ROLE_CONFIG_EDIT")]checked[/#if] />编辑配置项
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_CONFIG_REFRESH" [#if role.authorityList?contains("ROLE_CONFIG_REFRESH")]checked[/#if] />同步配置项
				</label>
				<label>
					<input type="checkbox" name="authorities" value="ROLE_CONFIG_DELETE" [#if role.authorityList?contains("ROLE_CONFIG_DELETE")]checked[/#if] />删除配置项
				</label>
			</span>
		</td>
	</tr>
[/@override]

[@override name="extrajs"]
	var $inputForm = $("#inputForm");
	var $selectAll = $("#inputForm .selectAll");

	$selectAll.click(function() {
		var $this = $(this);
		var $parentsNode = $this.parent().parent();
		var $thisCheckbox = $this.closest("tr").find(":checkbox");
		if ($thisCheckbox.filter(":checked").length > 0) {
			$thisCheckbox.prop("checked", false);
			$parentsNode.next().closest("tr").find(":checkbox").prop("checked", false);
		} else {
			$thisCheckbox.prop("checked", true);
			$parentsNode.next().closest("tr").find(":checkbox").prop("checked", true);
		}
		return false;
	});

	$("input[name='authorities']").each(function(i){
		$(this).on("click",function(){
			var parentRoreName = this.id;
			var subRoleCount = $("."+parentRoreName).length;
			if(subRoleCount > 0){
				if(this.checked){
					$("."+parentRoreName+" input[name='authorities']").prop("checked", true);
				}else{
					$("."+parentRoreName+" input[name='authorities']").prop("checked", false);
				}
			}
		});
	});
[/@override]

[@override name="rules"]
	description: {
		maxlength:64
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]