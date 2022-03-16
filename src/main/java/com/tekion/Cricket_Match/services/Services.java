package com.tekion.Cricket_Match.services;
import com.tekion.Cricket_Match.beans.CountryName;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Services {
     void playMatch() throws SQLException, IOException, ClassNotFoundException, InterruptedException;
     List<CountryName> getTeamsService() throws SQLException, ClassNotFoundException;
}
