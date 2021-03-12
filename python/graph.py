from mpl_toolkits.mplot3d import Axes3D
from matplotlib import pyplot as plt
import numpy as np
import pandas as pd

x = np.arange(-3.0, 12.1, 0.01)
y = np.arange(4.1, 5.8, 0.01)
X, Y = np.meshgrid(x, y)
Z = 21.5 + X * np.sin(4 * np.pi * X) + Y * np.sin(20 * np.pi * Y)

fig = plt.figure()
ax = Axes3D(fig)
ax.set_zlabel('z')
plt.title('Axes3D')
plt.xlabel('x')
plt.ylabel('y')
ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap='rainbow')
plt.savefig(r'Axes3D.png', dpi=1500)
plt.show()

plt.figure('Contourf')
plt.xlabel('x')
plt.ylabel('y')
plt.contourf(X, Y, Z, 8, cmap='jet')
plt.savefig(r'Contourf.png', dpi=1500)
plt.show()

df = pd.read_csv(r'../result.csv', sep=',', index_col=False)
pcs = np.array(df['pc'].unique())
pcs.sort()
pms = np.array(df['pm'].unique())
pms.sort()

avg = np.array([
    [
        (df[(df['pc'] == pc) & (df['pm'] == pm)])['avg'].values[0]
        for pc in pcs
    ]
    for pm in pms
])
fig = plt.figure()
plt.xlabel('pc')
plt.ylabel('pm')
plt.contourf(pcs, pms, avg, 20, cmap='jet')
plt.savefig(r'ParameterInvestigation.png', dpi=1500)
plt.show()
df = pd.DataFrame(avg)
df.columns = pcs
df.index = pms
df.to_csv(r'ParameterInvestigation.csv')
