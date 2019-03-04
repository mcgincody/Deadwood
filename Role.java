
public class Role
{
	RoleType roleType;
	RoleSpace roleSpace;
	
	public Role (RoleType roleType, RoleSpace roleSpace)
	{
		this.roleType = roleType;
		this.roleSpace = roleSpace;
	}
	
	public RoleSpace getRoleSpace()
	{
		return roleSpace;
	}

	public void setRoleSpace(RoleSpace roleSpace)
	{
		this.roleSpace = roleSpace;
	}
	
	public RoleType getRoleType()
	{
		return this.roleType;
	}
	
	public void setRoleType(RoleType roleType)
	{
		this.roleType = roleType;
	}
	
	public int[] getRollRewards(boolean rollWasSuccessful)
	{
		if (rollWasSuccessful)
		{
			switch (this.roleType)
			{
			case Extra:
				return new int[] { 1, 1 };
			case Star:
				return new int[] { 0, 2 };
			default:
				return new int[] { 0, 0 };
			}
		} 
		else
		{
			switch (this.roleType)
			{
			case Extra:
				return new int[] { 1, 0 };
			default:
				return new int[] { 0, 0 };
			}
		}
	}
}
