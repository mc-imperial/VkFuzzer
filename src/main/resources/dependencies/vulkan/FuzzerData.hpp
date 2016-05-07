#ifndef _FUZZER_FUZZER_DATA_HPP_
#define _FUZZER_FUZZER_DATA_HPP_

#define XYZ1(_x_, _y_, _z_) (_x_), (_y_), (_z_), 1.f

struct Vertex
{
    float posX, posY, posZ, posW;    // Position data
    float r, g, b, a;                // Color
};

struct VertexBuffer
{
    VkBuffer buffer;
    VkDeviceMemory deviceMemory;
};

const Vertex triData[] =
{
    { XYZ1( -0.25, -0.25, 0 ), XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1(  0.25, -0.25, 0 ), XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1(  0,  0.25, 0 ),    XYZ1( 1.f, 0.f, 0.f ) },
    { XYZ1( -0.75, -0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1( -0.25, -0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1(  -0.5,  0.25, 0 ), XYZ1( 0.f, 1.f, 0.f ) },
    { XYZ1(  0.25, -0.25, 0 ), XYZ1( 0.f, 0.f, 1.f ) },
    { XYZ1(  0.75, -0.25, 0 ), XYZ1( 0.f, 0.f, 1.f ) },
    { XYZ1(  0.5,  0.25, 0 ),  XYZ1( 0.f, 0.f, 1.f ) },
};

#endif // _FUZZER_FUZZER_DATA_HPP_