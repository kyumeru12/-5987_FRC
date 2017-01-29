import commands
print commands.getstatusoutput('hostname -I')[1]
