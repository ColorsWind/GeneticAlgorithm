# 遗传算法实现
实现简易的遗传算法优化下面函数的最大值

<img src="./function.png" alt="function" style="zoom:50%;" />

### 一、函数的图像。

使用`matplotlib`绘图，代码如下：

```python
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
from matplotlib import pyplot as plt

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
plt.savefig(r'Axes3D.png', dpi=500)
plt.show()

plt.figure('Contourf')
plt.xlabel('x')
plt.ylabel('y')
plt.contourf(X, Y, Z, 8, cmap='jet')
plt.savefig(r'Contourf.png', dpi=500)
plt.show()

```

绘图如下：

![Axes3D](./python/Axes3D.png)

![Contourf](./python/Contourf.png)



### 二、对《演化程序》一书的P42页的函数进行求解。

程序使用`kotlin`编写，使用`maven`管理依赖

代码较长，编写的程序入口在`AlgorithmMain.kt`

下面是一次求解的结果：

```
Chromosome(x=11.136993169729672, y=4.621410827587239, fitness=37.01514440205257)
```

其中参数设定为：

```
N=100, pc=0.7, pm=0.07, Gmax=2000，sigma=0.05
```

变异算子使用高斯分布编译，高斯分布的参数`μ=原基因的值`，`σ=sigma`。







### 三、参数pc和pm进行调查

编写的程序入口在`ParameterInvestigation.kt`

使用的参数为：

```
N=100, Gmax=2000，sigma=0.05, repeat=30
```

求解结果：

| pc   | pm   | avg      | worst    | worst-x  | worst-y  | best     | best-x   | best-y   |
| ---- | ---- | -------- | -------- | -------- | -------- | -------- | -------- | -------- |
| 0.5  | 0.02 | 36.3964  | 32.12148 | 7.566547 | 5.128484 | 38.8475  | 11.62667 | 5.724665 |
| 0.4  | 0.03 | 36.19325 | 32.93031 | 7.654059 | 5.435567 | 38.84597 | 11.62746 | 5.724755 |
| 0.3  | 0.04 | 35.87939 | 32.13072 | 7.073304 | 5.026695 | 38.84298 | 11.62465 | 5.725807 |
| 0.7  | 0.05 | 36.51508 | 32.44763 | 5.634403 | 5.620069 | 38.83798 | 11.62355 | 5.72592  |
| 0.4  | 0.01 | 36.41081 | 32.77068 | 11.0494  | 5.130343 | 38.82344 | 11.62371 | 5.723594 |
| 0.8  | 0.02 | 36.93637 | 35.09514 | 10.13535 | 4.71355  | 38.81767 | 11.62577 | 5.726743 |
| 0.4  | 0.05 | 35.91301 | 31.96978 | 5.670254 | 5.726756 | 38.80885 | 11.62961 | 5.723519 |
| 1    | 0.05 | 36.26624 | 33.74987 | 10.18306 | 4.930305 | 38.79714 | 11.63236 | 5.726008 |
| 0.8  | 0.05 | 36.3084  | 31.56553 | 8.569975 | 5.138225 | 38.78536 | 11.6177  | 5.725911 |
| 0.8  | 0.01 | 36.47517 | 33.43283 | 7.147222 | 5.229028 | 38.76523 | 11.63083 | 5.722749 |
| 0.7  | 0.1  | 36.46414 | 32.3187  | 5.129849 | 5.726581 | 38.74668 | 11.6237  | 5.625251 |
| 0.6  | 0.06 | 36.23779 | 30.96395 | 8.547578 | 4.930368 | 38.7292  | 11.62986 | 5.728082 |
| 0.6  | 0.02 | 36.66735 | 33.35938 | 7.14555  | 5.027789 | 38.71994 | 11.61361 | 5.724999 |
| 0.8  | 0.08 | 36.48859 | 33.54421 | 7.114005 | 5.733149 | 38.70272 | 11.6276  | 5.72147  |
| 0.1  | 0.09 | 36.26935 | 31.95358 | 5.658238 | 5.630632 | 38.6954  | 11.62335 | 5.627178 |
| 0.1  | 0.06 | 36.14719 | 33.47115 | 7.60821  | 4.729621 | 38.69127 | 11.62342 | 5.62727  |
| 0.6  | 0.05 | 35.99036 | 33.10771 | 10.55329 | 5.732808 | 38.69068 | 11.6208  | 5.728557 |
| 1    | 0.07 | 36.89037 | 33.87418 | 10.69007 | 5.127519 | 38.66474 | 12.09866 | 5.724595 |
| 0.7  | 0.03 | 35.94555 | 31.99723 | 9.708122 | 5.624431 | 38.66175 | 11.61951 | 5.721329 |
| 0.3  | 0.09 | 36.10898 | 32.28832 | 8.670478 | 4.315029 | 38.64605 | 11.61938 | 5.728925 |
| 0.4  | 0.07 | 36.56367 | 33.58416 | 10.69841 | 5.624809 | 38.63975 | 11.62726 | 5.525894 |
| 1    | 0.08 | 36.12266 | 33.16365 | 8.6595   | 5.213023 | 38.63805 | 11.62195 | 5.524855 |
| 1    | 0.1  | 36.69352 | 32.8923  | 10.56019 | 4.518483 | 38.63274 | 11.64024 | 5.726363 |
| 0.9  | 0.06 | 36.70869 | 32.53025 | 6.111961 | 5.121528 | 38.63234 | 11.63614 | 5.728238 |
| 1    | 0.01 | 36.09472 | 32.442   | 7.656282 | 5.136421 | 38.61015 | 11.63502 | 5.721297 |
| 0.1  | 0.05 | 36.10588 | 29.79647 | 9.036881 | 5.016127 | 38.60569 | 11.61857 | 5.525088 |
| 0.5  | 0.03 | 36.6095  | 33.3837  | 9.175106 | 4.629211 | 38.5968  | 11.63342 | 5.622092 |
| 0.7  | 0.02 | 36.01754 | 31.21597 | 5.157138 | 5.617307 | 38.59311 | 11.62467 | 5.628807 |
| 0.7  | 0.08 | 36.28679 | 32.92107 | 11.54852 | 5.231453 | 38.57105 | 11.61654 | 5.525719 |
| 0.1  | 0.08 | 35.99639 | 31.33417 | 5.655939 | 4.820175 | 38.56941 | 11.62317 | 5.621056 |
| 0.5  | 0.06 | 36.36198 | 34.03674 | 8.590833 | 4.828287 | 38.54679 | 11.62522 | 5.424483 |
| 0.6  | 0.1  | 36.20761 | 33.62728 | 10.18778 | 5.027827 | 38.54315 | 11.62296 | 5.424741 |
| 0.7  | 0.01 | 36.41268 | 33.28527 | 7.65088  | 5.132755 | 38.53899 | 11.62904 | 5.424948 |
| 0.2  | 0.07 | 36.62992 | 33.16236 | 11.69968 | 5.433081 | 38.52414 | 11.63727 | 5.525084 |
| 0.5  | 0.1  | 36.2679  | 33.69059 | 8.636766 | 5.312048 | 38.52284 | 11.63062 | 5.424451 |
| 0.7  | 0.09 | 35.9902  | 31.04006 | 5.14655  | 4.627251 | 38.51954 | 11.62278 | 5.423558 |
| 0.1  | 0.07 | 36.43935 | 33.85574 | 7.666771 | 5.72446  | 38.51666 | 11.62137 | 5.730369 |
| 0.7  | 0.04 | 36.1597  | 31.88147 | 6.166735 | 5.12789  | 38.51454 | 11.6447  | 5.724959 |
| 0.3  | 0.01 | 36.53035 | 33.03345 | 11.54566 | 5.630758 | 38.51445 | 11.63258 | 5.527929 |
| 0.2  | 0.03 | 36.39526 | 33.16624 | 7.115499 | 4.728701 | 38.50452 | 11.60946 | 5.625967 |
| 0.6  | 0.07 | 36.14897 | 32.18504 | 5.619003 | 5.320219 | 38.49864 | 12.0978  | 5.623562 |
| 0.8  | 0.1  | 36.50619 | 34.08066 | 8.590566 | 4.822922 | 38.49299 | 12.09705 | 5.727878 |
| 0.5  | 0.04 | 36.53523 | 33.3663  | 12.0565  | 5.336477 | 38.49048 | 11.61265 | 5.525878 |
| 0.7  | 0.07 | 36.35672 | 34.00819 | 12.05007 | 5.425949 | 38.4876  | 11.63887 | 5.52507  |
| 0.5  | 0.01 | 35.86275 | 30.80391 | 8.200422 | 4.525875 | 38.48686 | 11.64071 | 5.622865 |
| 1    | 0.03 | 36.57622 | 33.40766 | 6.627075 | 5.327052 | 38.46608 | 11.63189 | 5.422945 |
| 0.4  | 0.08 | 36.28754 | 33.36873 | 8.074251 | 5.427064 | 38.45704 | 11.64475 | 5.72282  |
| 0.6  | 0.08 | 36.59863 | 33.62637 | 9.179255 | 5.026666 | 38.45414 | 11.61604 | 5.426166 |
| 1    | 0.06 | 36.6038  | 33.09046 | 10.55325 | 5.229254 | 38.44478 | 11.62935 | 5.427985 |
| 0.2  | 0.04 | 35.79006 | 31.40517 | 5.158868 | 5.429578 | 38.43655 | 11.60712 | 5.625634 |
| 0.4  | 0.06 | 36.49579 | 33.34001 | 8.100651 | 4.126136 | 38.43458 | 11.6148  | 5.424079 |
| 0.5  | 0.05 | 36.30541 | 34.14781 | 7.123451 | 5.720831 | 38.4211  | 11.62882 | 5.323691 |
| 0.9  | 0.02 | 36.26565 | 32.71958 | 10.18803 | 4.915487 | 38.42034 | 11.62137 | 5.630386 |
| 0.7  | 0.06 | 36.22513 | 33.58283 | 11.69763 | 5.318859 | 38.40268 | 11.61945 | 5.326185 |
| 0.4  | 0.02 | 35.87267 | 33.77983 | 8.67317  | 5.320928 | 38.38286 | 11.61579 | 5.422305 |
| 0.1  | 0.01 | 35.6951  | 30.30794 | 5.146495 | 5.312892 | 38.38114 | 11.63898 | 5.424453 |
| 0.4  | 0.09 | 36.15693 | 32.73341 | 7.578079 | 5.120699 | 38.37411 | 11.62679 | 5.322377 |
| 0.1  | 0.02 | 36.41809 | 30.60886 | 8.215283 | 5.727832 | 38.37199 | 11.61627 | 5.731023 |
| 0.2  | 0.05 | 35.99539 | 32.63159 | 8.077058 | 4.527388 | 38.36725 | 12.0987  | 5.425461 |
| 0.2  | 0.1  | 36.36328 | 32.01237 | 7.577053 | 5.435621 | 38.36714 | 11.61603 | 5.325168 |
| 0.9  | 0.04 | 36.55567 | 33.41973 | 9.559916 | 5.423061 | 38.3651  | 11.63019 | 5.421106 |
| 0.6  | 0.09 | 36.4029  | 31.88043 | 6.68168  | 5.619806 | 38.3613  | 11.62059 | 5.63082  |
| 0.8  | 0.04 | 36.19339 | 32.43698 | 6.105853 | 5.026398 | 38.34803 | 11.64226 | 5.522983 |
| 1    | 0.02 | 36.26054 | 33.53281 | 7.606236 | 4.728129 | 38.33928 | 11.62079 | 5.731676 |
| 0.4  | 0.04 | 36.22981 | 32.78559 | 9.187404 | 4.827197 | 38.30543 | 11.60354 | 5.62564  |
| 0.3  | 0.03 | 36.0847  | 31.2098  | 4.630009 | 5.126938 | 38.30256 | 11.6291  | 5.530624 |
| 0.9  | 0.03 | 36.25513 | 32.68811 | 6.114893 | 5.221889 | 38.30073 | 11.11963 | 5.723761 |
| 0.2  | 0.09 | 36.1272  | 31.7812  | 9.710743 | 5.723107 | 38.28689 | 11.64015 | 5.42757  |
| 0.9  | 0.09 | 36.60617 | 34.49428 | 7.622636 | 5.730636 | 38.28341 | 11.12654 | 5.727464 |
| 0.5  | 0.08 | 36.13758 | 32.11186 | 8.557841 | 4.925107 | 38.27979 | 11.60523 | 5.7292   |
| 0.9  | 0.07 | 35.94876 | 33.63933 | 10.69098 | 4.925919 | 38.27617 | 11.63262 | 5.519531 |
| 0.9  | 0.05 | 36.55083 | 33.36682 | 7.598641 | 4.722885 | 38.27533 | 11.62372 | 5.519171 |
| 0.9  | 0.08 | 36.28224 | 31.74242 | 5.66367  | 5.519859 | 38.26543 | 11.64172 | 5.730598 |
| 1    | 0.09 | 36.27235 | 32.41556 | 8.694155 | 5.428429 | 38.23015 | 11.11945 | 5.727826 |
| 0.6  | 0.04 | 35.90296 | 31.99003 | 5.143031 | 5.720355 | 38.22988 | 11.63011 | 5.228187 |
| 0.1  | 0.1  | 35.79911 | 30.42306 | 8.206735 | 5.118348 | 38.2157  | 12.0967  | 5.6298   |
| 0.3  | 0.06 | 36.41529 | 33.72011 | 8.59577  | 5.015782 | 38.21552 | 11.60442 | 5.526648 |
| 0.2  | 0.02 | 36.4081  | 33.88031 | 9.124019 | 4.135567 | 38.20432 | 11.64753 | 5.729311 |
| 0.2  | 0.08 | 36.37397 | 33.40322 | 8.582337 | 4.525053 | 38.18335 | 11.61665 | 5.518993 |
| 0.3  | 0.07 | 36.02245 | 32.62747 | 11.05297 | 4.326733 | 38.18234 | 11.13681 | 5.722795 |
| 0.2  | 0.06 | 36.22861 | 31.99901 | 9.048149 | 5.521055 | 38.16698 | 12.09341 | 5.720707 |
| 0.8  | 0.06 | 36.0392  | 32.62516 | 7.565076 | 5.728301 | 38.15722 | 11.61752 | 5.430658 |
| 0.3  | 0.08 | 36.47931 | 33.96346 | 10.67426 | 5.113194 | 38.1491  | 11.64204 | 5.327273 |
| 0.6  | 0.03 | 36.696   | 34.8467  | 12.05955 | 5.222185 | 38.14421 | 11.13434 | 5.721536 |
| 0.3  | 0.05 | 35.82736 | 30.63133 | 12.03147 | 4.526839 | 38.12587 | 11.65311 | 5.723336 |
| 0.5  | 0.09 | 36.22378 | 33.44    | 11.68393 | 4.712422 | 38.12165 | 12.09773 | 5.420736 |
| 0.8  | 0.09 | 35.78208 | 32.10101 | 11.54111 | 4.92665  | 38.10804 | 11.11146 | 5.727498 |
| 0.9  | 0.01 | 36.21316 | 32.18705 | 10.20958 | 5.725627 | 38.09439 | 11.64676 | 5.521395 |
| 0.9  | 0.1  | 36.41345 | 31.37149 | 5.106317 | 4.926478 | 38.08722 | 11.60845 | 5.429338 |
| 0.2  | 0.01 | 36.2152  | 33.46498 | 8.157232 | 4.52759  | 38.08444 | 11.6076  | 5.520032 |
| 0.8  | 0.03 | 36.30809 | 33.55502 | 8.667682 | 4.626555 | 38.03534 | 11.13724 | 5.720873 |
| 0.3  | 0.02 | 36.02793 | 32.5286  | 10.17123 | 5.507584 | 38.02986 | 11.14466 | 5.725404 |
| 0.1  | 0.03 | 35.84126 | 33.02848 | 11.06008 | 4.230835 | 38.02655 | 12.09859 | 5.123135 |
| 0.6  | 0.01 | 35.92771 | 32.56422 | 9.067449 | 4.429329 | 38.01549 | 11.64983 | 5.522064 |
| 0.4  | 0.1  | 35.86527 | 31.39601 | 7.177855 | 4.832939 | 37.97429 | 11.60531 | 5.225556 |
| 0.8  | 0.07 | 36.3018  | 33.77272 | 7.104777 | 5.52849  | 37.96714 | 11.61447 | 5.431723 |
| 1    | 0.04 | 35.93406 | 33.60925 | 10.69409 | 5.321574 | 37.84819 | 11.63433 | 5.51676  |
| 0.5  | 0.07 | 36.24244 | 34.22647 | 11.18524 | 4.626827 | 37.84106 | 11.13249 | 5.530014 |
| 0.1  | 0.04 | 34.95195 | 30.08857 | 6.067255 | 5.436625 | 37.83846 | 11.12283 | 5.530359 |
| 0.3  | 0.1  | 36.00815 | 32.35738 | 7.137923 | 4.63462  | 37.64322 | 11.60065 | 5.617988 |

![Contourf](./python/ParameterInvestigation.png)
可以看出参数`pc`和`pm`对实验结果有影响，在本次实验中，最佳的参数为`pc=0.5`，`pm=0.02`。

