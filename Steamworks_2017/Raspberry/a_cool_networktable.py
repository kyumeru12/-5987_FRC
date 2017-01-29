from networktables import NetworkTable
from time import sleep


class SmartDashboard:

    def __init__(self, team_no=5987):
        NetworkTable.initialize(server="roborio-{}-frc.local".format(team_no))
        self.table = NetworkTable.getTable('SmartDashboard')

    def __setitem__(self, key, value):
        value_type = type(value)
        if value_type is str:
            self.table.putString(key, value)
        elif value_type is int or value_type is float:
            self.table.putNumber(key, value)
        elif value_type is bool:
            self.table.putBoolean(key, value)

    def __getitem__(self, key):
        value_type = type(value)
        try:
            self.table.getString(key)
        except:
            try:
                self.table.getNumber(key)
            except:
                self.table.getBoolean(key)
