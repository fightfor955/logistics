package cn.zbw.logistics.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zbw.logistics.pojo.Permission;
import cn.zbw.logistics.pojo.PermissionExample;
import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.pojo.UserExample.Criteria;
import cn.zbw.logistics.service.PermissionService;
import cn.zbw.logistics.service.UserService;

public class CustomRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*
		 *认证思路：
		 *1.先根据当前的身份（账号）去数据库中查询出对应的User对象 
		 * 如果返回结果为null,说明数据库没有此账号
		 * 如果有：返回user对象，user对象中封装此用户的所有信息
		 *2.在进行认证，把当前用户对象对应的密码传递给认证对象
		 * 最终交给shiro底层认证
		 */
		String username = (String) token.getPrincipal();
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = userService.selectByExample(example);
		
		System.out.println("users :"+users);
		
		if(users.size()==0) {
			return null;
		}
		//获取user对象
		User principal = users.get(0);
		//获取user对象密码
		String credentials = principal.getPassword();
		System.out.println(credentials);
		//获取盐
		ByteSource salt = ByteSource.Util.bytes(principal.getSalt());
		System.out.println(principal.getSalt());
		//创建人在信息
		//SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, this.getName());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials,salt, this.getName());
		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		 * 授权思路
		 * 1.获取当前认证的身份信息  User对象
		 * 2.根据 User对象的 roleId 角色id 查询出角色信息对象 Role对象
		 * 3,根据角色的 permissionIds 的值切割成 数组
		 * 	 permissionIds ：10,1,13,15,16,17,11,18,19,20,21,12,22,23,24,25
		 *   最终转换成 Long的集合
		 *   
		 * 4，使用permissionService 的条件查询使用  in查询把集合做为参数查询出所有对应的 权限对象 
		 * 	
		 * PermissionExample example = new PermissionExample();
			cn.zj.logistic.pojo.PermissionExample.Criteria criteria = example.createCriteria();
			//使用in查询
			criteria.andPermissionIdIn(pemissionIdsList);
			
		   5,循环集合分别获取出每个权限对象的权限表达式  expression 的值
		   6，再将每个权限表达式的值设置shiro框架
		 * 
		 */
		User user = (User) principals.getPrimaryPrincipal();
		Long roleId = user.getRoleId();
		String permissionIds = user.getPermissionIds();
		if(StringUtils.isNotBlank(permissionIds)) {
			String[] permissionIdsArr = permissionIds.split(",");
			List<Long> permissionIdsArrByLong = new ArrayList<Long>();
			for (String permissionId : permissionIdsArr) {
				permissionIdsArrByLong.add(Long.valueOf(permissionId));
			}
			PermissionExample example = new PermissionExample();
			cn.zbw.logistics.pojo.PermissionExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andPermissionIdIn(permissionIdsArrByLong);
			List<Permission> permissions = permissionService.selectByExample(example);
			//创建授权对象
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			for (Permission permission : permissions) {
				System.out.println(permission);
				if(StringUtils.isNotBlank(permission.getExpression())) {
					authorizationInfo.addStringPermission(permission.getExpression());
				}
			}
			return authorizationInfo;
		}
		return null;
	}


}
