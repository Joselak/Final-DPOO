import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-4, 2, 400)
y_parabola = 9 - x**2
y_line = 2*x + 6

plt.figure(figsize=(8, 6))
plt.plot(x, y_parabola, label=r'$y = 9 - x^2$', color='blue')
plt.plot(x, y_line, label=r'$y = 2x + 6$', color='green')
plt.axhline(y=0, color='black', linestyle='--')

# Rellenar la región L
x_fill = np.linspace(-3, 1, 100)
y_fill_upper = 9 - x_fill**2
plt.fill_between(x_fill, 0, y_fill_upper, color='gray', alpha=0.3, label='Región L')

plt.scatter([-3, 1], [0, 8], color='red')  # Puntos de intersección
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid()
plt.title('Región L para el cálculo de la masa')
plt.show()