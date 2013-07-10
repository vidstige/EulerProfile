using System;
using EulerProfile.Model;
using System.Net;
using System.Threading.Tasks;
using EulerProfile.Common;

namespace EulerProfile.Repositories
{
    class ProjectEulerRepository
    {
        public async Task<UserProfile> GetByUsername(string username)
        {
            Uri uri = new Uri("http://projecteuler.net/profile/" + username + ".txt");
            var webClient = new WebClient();
            string rawProfile = await webClient.DownloadString(uri);
            var parts = rawProfile.Split(',');
            if (parts.Length < 5) throw new ApplicationException(string.Format("User {0} not found", username));
            return new UserProfile(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
    }
}
