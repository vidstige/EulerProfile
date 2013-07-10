using System;

namespace EulerProfile.Model
{
    class UserProfile
    {
        private string _username;
        private string _country;
        private string _unknown;
        private string _solved;
        private string _level;

        public UserProfile(string username, string country, string unknown, string solved, string level)
        {
            _username = username;
            _country = country;
            _unknown = unknown;
            _solved = solved;
            _level = level;
        }

        public Uri LevelIcon
        {
            get { return new Uri("http://projecteuler.net/images/levels/level_1.png"); }
        }
    }
}
